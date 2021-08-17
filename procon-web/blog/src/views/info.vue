<template>
  <article>
    <el-dialog :visible.sync="dialogPictureVisible" fullscreen>
      <img :src="dialogImageUrl" alt="dialogImageUrl" style="margin: 0 auto;" />
    </el-dialog>
    <h1 class="t_nav">
      <a href="/" class="n1">网站首页</a>
      <a
        href="javascript:void(0);"
        v-if="blogData.blogSort.id"
        @click="goToSortList(blogData.blogSort.id)"
        class="n2"
      >{{blogData.blogSort ? blogData.blogSort.sortName:""}}</a>
    </h1>
    <div class="infosbox">
      <div class="newsview">
        <h3 class="news_title" v-if="blogData.title">{{blogData.title}}</h3>
        <div class="bloginfo" v-if="blogData.blogSort.id">
          <ul>
            <li class="author">
              <span class="iconfont">&#xe60f;</span>
              <a href="javascript:void(0);" @click="goToAuthor(blogData.author)">{{blogData.author}}</a>
            </li>
            <li class="lmname">
              <span class="iconfont">&#xe603;</span>
              <a
                href="javascript:void(0);"
                @click="goToSortList(blogData.blogSort.id)"
              >{{blogData.blogSort ? blogData.blogSort.sortName:""}}</a>
            </li>
            <li class="createTime">
              <span class="iconfont">&#xe606;</span>
              {{blogData.createTime}}
            </li>
            <li class="view">
              <span class="iconfont">&#xe8c7;</span>
              {{blogData.clickCount}}
            </li>
            <li class="like">
              <span class="iconfont">&#xe663;</span>
              {{blogData.collectCount}}
            </li>
          </ul>
        </div>
        <div class="tags">
          <a
            v-if="blogData.tagList"
            v-for="item in blogData.tagList"
            :key="item.id"
            href="javascript:void(0);"
            @click="goToList(item.id)"
            target="_blank"
          >{{item.content}}</a>
        </div>
        <div class="news_about">
          <strong>版权</strong>
          {{blogData.copyright}}
        </div>
        <div
          class="news_con ck-content"
          v-html="blogContent"
          v-highlight
          @click="imageChange"
        >{{blogContent}}</div>
      </div>

      <!--付款码和点赞-->
      <PayCode v-if="openAdmiration == '1'" :blogId="blogId" @addPraiseCount="addPraiseCount" :praiseCount="blogData.collectCount"></PayCode>

      <div class="otherlink" v-if="sameBlogData.length > 0">
        <h2>相关文章</h2>
        <ul>
          <li v-for="item in sameBlogData" :key="item.id">
            <a
              href="javascript:void(0);"
              @click="goToInfo(item.id)"
              :title="item.title"
            >{{subText(item.title, 18)}}</a>
          </li>
        </ul>
      </div>
      <div class="news_pl" :style="openCommentCss">
        <h2 v-if="openComment == '1'">文章评论</h2>
        <ul v-if="openComment == '1'">
          <CommentBox
            :userInfo="userInfo"
            :commentInfo="commentInfo"
            @submit-box="submitBox"
            :showCancel="showCancel"
          ></CommentBox>
          <div class="message_infos">
            <CommentList :comments="comments" :commentInfo="commentInfo"></CommentList>
            <div class="noComment" v-if="comments.length ==0">还没有评论，快来抢沙发吧！</div>
          </div>
        </ul>
      </div>
    </div>
    <div class="sidebar2" v-if="showSidebar">
      <side-catalog
        :class="vueCategory"
        v-bind="catalogProps"
      >
      </side-catalog>
    </div>
  </article>
</template>

<script>
import {getWebConfig} from "../api/blog/home";
import { getBlogById, getSameBlogByBlogId } from "../api/blog/content";
import { addComment, getCommentList } from "../api/blog/comment";
// vuex中有mapState方法，相当于我们能够使用它的getset方法
import { mapMutations } from "vuex";
import CommentList from "../components/CommentList";
import CommentBox from "../components/CommentBox";
import ThirdRecommend from "../components/Recommend/ThirdRecommend";
import FourthRecommend from "../components/Recommend/FourRecommned";
import TagCloud from "../components/Tags";
import HotBlog from "../components/HotBlog";
import FollowUs from "../components/ContactMe";
import PayCode from "../components/PayCode";
import Link from "../components/Link";
import { Loading } from "element-ui";
import Sticky from "../components/Sticky";
import SideCatalog from '../components/VueSideCatalog'
import $ from 'jquery'

export default {
  name: "info",
  components: {
    //注册组件
    FourthRecommend,
    ThirdRecommend,
    TagCloud,
    HotBlog,
    FollowUs,
    PayCode,
    CommentList,
    CommentBox,
    SideCatalog,
    Link,
    Sticky
  },
  data() {
    return {
      // 目录列表数
      catalogSum: 0,
      showStickyTop: false,
      showSideCatalog: true,
      showSidebar: true, //是否显示侧边栏
      blogContent: "",
      catalogProps: {
        // 内容容器selector(必需)
        container: '.ck-content',
        watch: true,
        levelList: ["h2", "h3"],
      },
      loadingInstance: null, // loading对象
      showCancel: false,
      submitting: false,
      comments: [],
      commentInfo: {
        // 评论来源： MESSAGE_BOARD，ABOUT，BLOG_INFO 等 代表来自某些页面的评论
        source: "BLOG_INFO",
        blogId: this.$route.query.blogId
      },
      pageNum: 1,
      pageSize: 10,
      total: 0, //总数量
      toInfo: {},
      userInfo: {},
      blogId:null, //传递过来的博客id
      blogData: {
        blogSort: {}
      },
      sameBlogData: [], //相关文章
      dialogPictureVisible: false,
      dialogImageUrl: "",
      openComment: 0, // 开启评论
      openAdmiration: 0, // 开启赞赏
    };
  },
  computed: {
    vueCategory: function () {
      if(!this.showStickyTop && this.showSideCatalog) {
        return 'catalog'
      }
      if(!this.showStickyTop && !this.showSideCatalog) {
        return 'catalog'
      }
      if(this.showStickyTop && this.showSideCatalog) {
        return 'catalog3'
      }
      if(this.showStickyTop && !this.showSideCatalog) {
        return 'catalog2'
      }
    },
    openCommentCss: function () {
      if(this.openComment == 0) {
        return {
          "min-height": "10px"
        }
      }
    },
  },
  mounted(){
    let that = this;
    getBlogById(this.blogId).then(response => {
        this.blogData = response.data;
        this.blogId = response.data.id
        this.commentInfo.blogId = response.data.id;
        this.getSameBlog()
        this.getCommentDataList();
      setTimeout(()=>{
        that.blogContent = response.data.content
        that.loadingInstance.close();
      }, 200)
    });

    let after = 0;
    let offset = 110;
    $(window).scroll(function () {
      let docHeight = $(document).height(); // 获取整个页面的高度(不只是窗口,还包括为显示的页面)
      let winHeight = $(window).height(); // 获取当前窗体的高度(显示的高度)
      let winScrollHeight = $(window).scrollTop(); // 获取滚动条滚动的距离(移动距离)

      if (winScrollHeight < offset) {
        that.showStickyTop = false
      } else {
        that.showStickyTop = true
      }

      if (winScrollHeight > after) {
        // console.log("隐藏顶部栏", winScrollHeight)
        that.showSideCatalog = true
      } else {
        // console.log("显示顶部栏", winScrollHeight)
        that.showSideCatalog = false
      }
      after = winScrollHeight;
      //还有30像素的时候,就查询
      if(docHeight == winHeight + winScrollHeight){
        if(that.comments.length >= that.total) {
          console.log('已经到底了')
          return;
        }
        let params = {};
        params.source = that.commentInfo.source;
        params.blogId = this.commentInfo.blogId;
        params.pageNum = that.pageNum + 1
        params.pageSize = that.pageSize;
        getCommentList(params).then(response => {
            that.comments = that.comments.concat(response.data.rows);
            that.setCommentList(that.comments);
            that.total = response.data.total;
        });
      }
    })

    // 屏幕自适应
    window.onresize = () => {
      return (() => {
        // 屏幕大于950px的时候，显示侧边栏
        that.showSidebar = document.body.clientWidth > 950
      })()
    }
  },
  created() {
    this.loadingInstance = Loading.service({
      fullscreen: true,
      text: "正在努力加载中~"
    });
    this.blogId = this.$route.query.blogId;
    this.setCommentAndAdmiration();
    // 屏幕大于950px的时候，显示侧边栏
    this.showSidebar = document.body.clientWidth > 950
  },
  methods:{
    //拿到vuex中的写的两个方法
    ...mapMutations(["setCommentList", "setWebConfigData"]),
    handleCurrentChange: function(val) {
      this.pageNum = val;
      this.getCommentDataList();
    },

    /**  获取相似博客*/
    getSameBlog() {
      getSameBlogByBlogId(this.blogId).then(response => {
          this.sameBlogData = response.rows;
      });
    },
    // 设置是否开启评论和赞赏
    setCommentAndAdmiration() {
      let webConfigData = this.$store.state.app.webConfigData
      if(webConfigData.createTime) {
        this.openAdmiration = 1
        this.openComment = webConfigData.startComment
      } else {
        getWebConfig().then(response => {
          webConfigData = response.data;
          // 存储在Vuex中
          this.setWebConfigData(response.data)
          this.openAdmiration = 1
          this.openComment = webConfigData.startComment
        });
      }
    },
    /**  提交评论*/
    submitBox(e) {
      let params = {};
      params.blogId = e.blogId;
      params.source = e.source;
      params.userId = e.userId;
      params.content = e.content;

      addComment(params).then(response => {
        this.$notify({
          title: "成功",
          message: "发表成功~",
          type: "success",
          offset: 100
        });
        this.getCommentDataList();
      });
    },
    /**  获取评论列表*/
    getCommentDataList: function() {
      let params = {};
      params.source = this.commentInfo.source;
      params.blogId = this.commentInfo.blogId;
      params.pageNum = this.pageNum;
      params.pageSize = this.pageSize;
      getCommentList(params).then(response => {
          this.comments = response.rows;
          this.setCommentList(this.comments);
          this.total = response.total
      });
    },

    //跳转到文章详情
    goToInfo(id) {
      let routeData = this.$router.resolve({
        path: "/info",
        query: { blogId: id }
      });
      window.open(routeData.href, "_blank");
    },
    //跳转到搜索详情页
    goToList(id) {
      let routeData = this.$router.resolve({
        path: "/list",
        query: { tagId: id }
      });
      window.open(routeData.href, "_blank");
    },
    //跳转到搜索详情页
    goToSortList(id) {
      let routeData = this.$router.resolve({
        path: "/list",
        query: { sortId: id }
      });
      window.open(routeData.href, "_blank");
    },
    //跳转到搜索详情页
    goToAuthor(author) {
      let routeData = this.$router.resolve({
        path: "/list",
        query: { author: author }
      });
      window.open(routeData.href, "_blank");
    },
    imageChange: function(e) {
      //首先需要判断点击的是否是图片
      let type = e.target.localName;
      if (type == "img") {
        // window.open(e.target.currentSrc);
        this.dialogPictureVisible = true;
        this.dialogImageUrl = e.target.currentSrc;
      }
    },
    //切割字符串
    subText: function(str, index) {
      if (str.length < index) {
        return str;
      }
      return str.substring(0, index) + "...";
    },
    addPraiseCount: function (val){
      return this.blogData.collectCount = val;
    }

  }
}

</script>

<style scoped>
.emoji-panel-wrap {
  box-sizing: border-box;
  border: 1px solid #cccccc;
  border-radius: 5px;
  background-color: #ffffff;
  width: 470px;
  height: 190px;
  position: absolute;
  z-index: 999;
  left: 35px;
  top: 10px;
}
.emoji-size-small {
  zoom: 0.3;
  margin: 5px;
  vertical-align: middle;
}
.emoji-size-large {
  zoom: 0.5;
margin: 5px;
}
.iconfont {
  font-size: 14px;
  margin-right: 3px;
}
.message_infos {
  width: 96%;
  margin-left: 10px;
}
.noComment {
  width: 100%;
  text-align: center;
}
.catalog {
  position: fixed;
  margin-left: 20px;
  /*max-height: 700px*/
}
.catalog2 {
  position: fixed;
  margin-left: 20px;
  top: 70px;
}
.catalog3 {
  position: fixed;
  margin-left: 20px;
  top: 20px;
}
.line-style {
  display: inline-block;
  height: 20px;
  width: 3px;
  background: transparent;
}
.line-style--active {
  background: currentColor;
}

</style>
