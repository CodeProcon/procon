<template>
  <div>
    <div class="pagebg sh"></div>
    <div class="container">
      <h1 class="t_nav">
        <span>慢生活，不是懒惰，放慢速度不是拖延时间，而是让我们在生活中寻找到平衡。</span>
        <a href="/" class="n1">网站首页</a>
        <a href="/" class="n2">搜索</a>
      </h1>

      <!--blogsbox begin-->
      <div class="blogsbox">
        <div
          v-for="item in blogData"
          :key="item.id"
          class="blogs"
          data-scroll-reveal="enter bottom over 1s"
        >
          <h3 class="blogtitle">
            <a
              href="javascript:void(0);"
              @click="goToInfo(item.id)"
              v-html="item.title"
            >{{item.title}}</a>
          </h3>
          <span class="blogpic">
            <a href="javascript:void(0);" @click="goToInfo(item.id)" title>
              <img v-if="item.photoUrl" :src="item.photoUrl" alt="">
            </a>
          </span>
          <p class="blogtext" v-html="item.summary">{{item.summary}}</p>
          <div class="bloginfo">
            <ul>
              <li class="author">
                <span class="iconfont">&#xe60f;</span>
                <a href="javascript:void(0);" @click="goToAuthor(item.author)">{{item.author}}</a>
              </li>
              <li class="lmname" v-if="item.blogSortName">
                <span class="iconfont">&#xe603;</span>
                <a href="javascript:void(0);" @click="goToList(item.blogSortid)">{{item.blogSortName}}</a>
              </li>
              <li class="createTime"><span class="iconfont">&#xe606;</span>{{item.createTime}}</li>
            </ul>
          </div>
        </div>

        <div class="isEnd">
          <div
            class="loadContent"
            @click="loadContent"
            v-if="!isEnd && !loading && totalPages>0"
          >点击加载更多</div>

          <div class="lds-css ng-scope" v-if="!isEnd && loading">
            <div style="width:100%;height:100%" class="lds-facebook">
              <div></div>
              <div></div>
              <div></div>
            </div>
          </div>

          <span v-if="blogData.length >= 0 && isEnd &&!loading && totalPages>0">我也是有底线的~</span>

          <span v-if="totalPages == 0 && !loading">空空如也~</span>
        </div>
      </div>
      <!--blogsbox end-->

      <div class="sidebar">
        <!-- 三级推荐 -->
        <ThirdRecommend></ThirdRecommend>

        <!--标签云-->
        <TagCloud></TagCloud>

        <!--四级推荐-->
        <FourthRecommend></FourthRecommend>

        <!--点击排行-->
        <HotBlog></HotBlog>

        <Link></Link>

        <!--关注我们-->
        <FollowUs></FollowUs>
      </div>
    </div>
  </div>
</template>

<script>

import ThirdRecommend from "../components/Recommend/ThirdRecommend";
import FourthRecommend from "../components/Recommend/FourRecommned";
import TagCloud from "../components/Tags";
import HotBlog from "../components/HotBlog";
import FollowUs from "../components/ContactMe";


export default {
  name: "list",
  data() {
    return {
      blogData: [],
      keywords: "",
      pageNum: 1,
      totalPages: 0,
      pageSize: 10,
      total: 0, //总数量
      tagId: "",
      searchBlogData: [], //搜索出来的文章
      sortId: "",
      isEnd: false, //是否到底底部了
      loading: false //内容是否正在加载
    };
  },
  components: {
    FourthRecommend,
    ThirdRecommend,
    TagCloud,
    HotBlog,
    FollowUs,
  },
  created() {
    this.keywords = this.$route.query.keyword;
    this.tagId = this.$route.query.tagId;
    this.sortId = this.$route.query.sortId;
    this.author = this.$route.query.author;

    if (this.keywords === undefined &&
      this.tagId === undefined &&
      this.sortId === undefined &&
      this.author === undefined) {
      return;
    }

    this.search();
  },
  mounted() {

  },
  watch: {
    $route(to, from) {
      this.keywords = this.$route.query.keyword;
      this.tagId = this.$route.query.tagId;
      this.sortId = this.$route.query.sortId;
      this.searchBlogData = [] // 清空查询出来的博客
      this.search();
    }
  },
  methods: {
    //跳转到文章详情
    goToInfo(id) {
      let routeData = this.$router.resolve({
        path: "/info",
        query: { blogId: id }
      });
      window.open(routeData.href, '_blank');
    },
    //点击了分类
    goToList(id) {
      let routeData = this.$router.resolve({
        path: "/list",
        query: { sortId: id }
      });
      window.open(routeData.href, '_blank');
    },
    goToAuthor(author) {
      let routeData = this.$router.resolve({
        path: "/list",
        query: {author: author}
      });
      window.open(routeData.href, '_blank');
    },
    // 加载内容
    loadContent: function() {
      let that = this;
      that.pageNum = that.pageNum + 1;
      that.search();
    },
    search: function() {

    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
</style>

<style>
.isEnd {
  float: left;
  width: 100%;
  height: 80px;
  text-align: center;
}

.ng-scope {
  margin: 0 auto;
  width: 18%;
  height: 10%;
}

.loadContent {
  width: 120px;
  height: 30px;
  line-height: 30px;
  font-size: 16px;
  margin: 0 auto;
  color: aliceblue;
  cursor: pointer;
  background: rgba(0, 0, 0, 0.8);
}

@keyframes lds-facebook_1 {
  0% {
    top: 0px;
    height: 200px;
  }
  50% {
    top: 80px;
    height: 40px;
  }
  100% {
    top: 80px;
    height: 40px;
  }
}
@-webkit-keyframes lds-facebook_1 {
  0% {
    top: 0px;
    height: 200px;
  }
  50% {
    top: 80px;
    height: 40px;
  }
  100% {
    top: 80px;
    height: 40px;
  }
}
@keyframes lds-facebook_2 {
  0% {
    top: 20px;
    height: 160px;
  }
  50% {
    top: 80px;
    height: 40px;
  }
  100% {
    top: 80px;
    height: 40px;
  }
}
@-webkit-keyframes lds-facebook_2 {
  0% {
    top: 20px;
    height: 160px;
  }
  50% {
    top: 80px;
    height: 40px;
  }
  100% {
    top: 80px;
    height: 40px;
  }
}
@keyframes lds-facebook_3 {
  0% {
    top: 40px;
    height: 120px;
  }
  50% {
    top: 80px;
    height: 40px;
  }
  100% {
    top: 80px;
    height: 40px;
  }
}
@-webkit-keyframes lds-facebook_3 {
  0% {
    top: 40px;
    height: 120px;
  }
  50% {
    top: 80px;
    height: 40px;
  }
  100% {
    top: 80px;
    height: 40px;
  }
}
.lds-facebook {
  position: relative;
}
.lds-facebook div {
  position: absolute;
  width: 20px;
}
.lds-facebook div:nth-child(1) {
  left: 40px;
  background: #1d0e0b;
  -webkit-animation: lds-facebook_1 1s cubic-bezier(0, 0.5, 0.5, 1) infinite;
  animation: lds-facebook_1 1s cubic-bezier(0, 0.5, 0.5, 1) infinite;
  -webkit-animation-delay: -0.2s;
  animation-delay: -0.2s;
}
.lds-facebook div:nth-child(2) {
  left: 90px;
  background: #774023;
  -webkit-animation: lds-facebook_2 1s cubic-bezier(0, 0.5, 0.5, 1) infinite;
  animation: lds-facebook_2 1s cubic-bezier(0, 0.5, 0.5, 1) infinite;
  -webkit-animation-delay: -0.1s;
  animation-delay: -0.1s;
}
.lds-facebook div:nth-child(3) {
  left: 140px;
  background: #d88c51;
  -webkit-animation: lds-facebook_3 1s cubic-bezier(0, 0.5, 0.5, 1) infinite;
  animation: lds-facebook_3 1s cubic-bezier(0, 0.5, 0.5, 1) infinite;
}
.lds-facebook {
  width: 90px !important;
  height: 90px !important;
  -webkit-transform: translate(-45px, -45px) scale(0.45) translate(45px, 45px);
  transform: translate(-45px, -45px) scale(0.45) translate(45px, 45px);
}
</style>

