<template>
  <div>
    <div class="pagebg tagPage"></div>
    <div class="container">
      <h1 class="t_nav">
        <span>内心没有阳光的人温暖不了别人。用心写博客，知识不是高谈阔论，是现实点点滴滴的积累。</span>
        <a href="/" class="n1">网站首页</a>
        <a href="javascript:void(0);" class="n2">标签</a>
      </h1>

      <div class="sortBox">
        <div class="time">
          <div class="block">
            <div class="radio" style="margin-bottom:20px;"></div>
            <el-timeline :reverse="reverse">
              <el-timeline-item v-for="(activity, index) in activities" hide-timestamp :key="index">
                <span
                  @click="getBlogTagList(activity.id)"
                  :class="[activity.id == selectBlogId ? 'sortBoxSpan sortBoxSpanSelect' : 'sortBoxSpan']"
                >{{activity.tagName}}</span>
              </el-timeline-item>
            </el-timeline>
          </div>
        </div>

        <div class="article">
          <div class="block" v-infinite-scroll="load">
            <el-timeline>
              <el-timeline-item
                v-for="item in itemByDate"
                :key="item.id"
                :timestamp="item.createTime"
                placement="top"
              >
                <el-card>
                  <h4 @click="goToList('blogContent', item)" class="itemTitle">{{item.title}}</h4>
                  <br>
                  <el-tag class="elTag" v-if="item.isOriginal==1" type="danger">原创</el-tag>
                  <el-tag class="elTag" v-if="item.isOriginal==0" type="info">转载</el-tag>

                  <el-tag
                    class="elTag"
                    v-if="item.author"
                    @click="goToList('author', item)"
                  >{{item.author}}</el-tag>

                  <el-tag
                    class="elTag"
                    type="success"
                    v-if="item.blogSort != null"
                    @click="goToList('blogSort', item)"
                  >{{item.blogSort.sortName}}</el-tag>
                  <el-tag
                    class="elTag"
                    v-for="tagItem in item.tagList"
                    v-if="tagItem != null"
                    :key="tagItem.id"
                    @click="goToList('tag', tagItem)"
                    type="warning"
                  >{{tagItem.content}}</el-tag>
                </el-card>
              </el-timeline-item>
            </el-timeline>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { getTagList, getArticleByTagId } from "@/api/blog/tag";
  export default {
    data() {
      return {
        selectBlogId: "",
        reverse: false,
        activities: [],
        itemByDate: [],
        articleByDate: {},
        count: 0,
        pageNum: 1,
        pageSize: 10
      };
    },
    components: {
      //注册组件
    },
    mounted() { },
    created() {
      getTagList().then(response => {
        let activities = response.rows;
        let result = [];
        for (let a = 0; a < activities.length; a++) {
          let dataForDate = {
            tagName: activities[a].content,
            id: activities[a].id
          };
          result.push(dataForDate);
          }
          this.activities = result;
          // 默认选择第一个
          this.getBlogTagList(activities[0].id);
      });

    },
    methods: {
      getBlogTagList(tagId) {
        this.selectBlogId = tagId;
        let params = {}
        params.tagId = tagId
        getArticleByTagId(params).then(response => {
          this.itemByDate = response.rows;
        });
      },
      load() {
        let params = {};
        if (this.selectBlogId == null || this.selectBlogId == "" || this.selectBlogId == undefined) {
          return;
        }
        params.tagId = this.selectBlogId;
        params.pageNum = this.pageNum +1;
        params.pageSize = this.pageSize;
        getArticleByTagId(params).then(response => {
          this.itemByDate = this.itemByDate.concat(response.rows);
        });
      },
      //跳转到搜索详情页
      goToList(type, entity) {
        switch (type) {
          case "tag":
          {
            // 标签id
            let routeData = this.$router.resolve({
              path: "/list",
              query: { tagId: entity.id }
            });
            window.open(routeData.href, "_blank");
          }
            break;
          case "blogSort":
          {
            let routeData = this.$router.resolve({
              path: "/list",
              query: { sortId: entity.blogSort.id }
            });
            window.open(routeData.href, "_blank");
          }
            break;
          case "author":
          {
            let routeData = this.$router.resolve({
              path: "/list",
              query: { author: entity.author }
            });
            window.open(routeData.href, "_blank");
          }
            break;

          case "blogContent":
          {
            let routeData = this.$router.resolve({
              path: "/info",
              query: { blogId: entity.id }
            });
            window.open(routeData.href, "_blank");
          }
            break;
        }
      }
    }
  };
</script>

<style>
  .sortBox {
    color: #555;
  }

  .sortBoxSpan {
    cursor: pointer;
  }
  .sortBoxSpan:hover {
    color: #409eff;
  }
  .sortBoxSpanSelect {
    color: #409eff;
  }

  .itemTitle {
    cursor: pointer;
  }
  .itemTitle:hover {
    color: #409eff;
  }
  .elTag {
    cursor: pointer;
  }
  .tagPage {
    background-size: 0;
    background-position: top center;
  }
  .pagebg {
    margin-top: 0px;
    padding: 45px 0;
    text-align: center;
    width: 100%;
  }
</style>
