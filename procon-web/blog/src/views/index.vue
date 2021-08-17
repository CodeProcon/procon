<template>
  <article>
    <!--banner begin-->
    <div class="picsbox">
      <FirstRecommend></FirstRecommend>
      <!--banner end-->

      <!-- 二级推荐 -->
      <SecondRecommend></SecondRecommend>
    </div>
    <div class="blank"></div>

    <!--blogsBox begin-->
    <div class="blogsbox">
      <div
        v-for="item in newBlogData"
        :key="item.uid"
        class="blogs"
        data-scroll-reveal="enter bottom over 1s"
      >
        <h3 class="blogtitle">
          <a href="javascript:void(0);" @click="goToInfo(item)">{{item.title}}</a>
        </h3>

        <span class="blogpic">
          <a href="javascript:void(0);" @click="goToInfo(item)"  title>
            <img v-if="item.photoList" :src="item.photoList[0]" alt>
          </a>
        </span>

        <p class="blogtext">{{item.summary}}</p>
        <div class="bloginfo">
          <ul>

            <li class="author">
              <span class="iconfont">&#xe60f;</span>
              <a href="javascript:void(0);" >{{item.author}}</a>
            </li>
            <li class="lmname" v-if="item.blogSort">
              <span class="iconfont">&#xe603;</span>
              <a
                href="javascript:void(0);"
              >{{item.blogSort.sortName}}</a>
            </li>
            <li class="view">
              <span class="iconfont">&#xe8c7;</span>
              <span>{{item.clickCount}}</span>
            </li>
            <li class="like">
              <span class="iconfont">&#xe663;</span>
              {{item.collectCount}}
            </li>
            <li class="createTime">
              <span class="iconfont">&#xe606;</span>
              {{item.createTime}}
            </li>
          </ul>
        </div>
      </div>

      <div class="isEnd">
        <!-- <span v-if="!isEnd">正在加载中~</span> -->

        <div class="loadContent" @click="loadContent" v-if="!isEnd&&!loading">点击加载更多</div>

        <div class="lds-css ng-scope" v-if="!isEnd&&loading">
          <div style="width:100%;height:100%" class="lds-facebook">
            <div></div>
            <div></div>
            <div></div>
          </div>
        </div>

        <span v-if="isEnd">我也是有底线的~</span>
      </div>
    </div>
    <!--blogsBox end-->

    <div class="sidebar">

      <!-- 三级推荐 -->
      <ThirdRecommend></ThirdRecommend>

      <!--四级推荐-->
      <FourthRecommend></FourthRecommend>
      <!--点击排行-->
      <HotBlog></HotBlog>
      <!--标签云-->
      <Tags></Tags>
      <!--关注我们-->
      <ContactMe></ContactMe>
      <!-- 友情链接-->
      <FriendlyLink></FriendlyLink>

    </div>

  </article>
</template>

<script>
import FirstRecommend from "../components/Recommend/FirstRecommend";
import SecondRecommend from "../components/Recommend/SecondRecommend";
import ThirdRecommend from "../components/Recommend/ThirdRecommend";
import FourthRecommend from "../components/Recommend/FourRecommned";
import Tags from "../components/Tags";
import HotBlog from "../components/HotBlog";
import ContactMe from "../components/ContactMe";
import FriendlyLink from "../components/Link";
import {Loading} from "element-ui";
import {getBlogList} from "@/api/blog/home";

export default {
  name: "index.vue",

  components:{
    // 注册组件
    FirstRecommend,
    SecondRecommend,
    ThirdRecommend,
    FourthRecommend,
    Tags,
    HotBlog,
    ContactMe,
    FriendlyLink
  },
  data(){
    return{
      loadingInstance: null, // loading对象
      firstData: [], //；一级推荐数据
      secondData: [], //；二级级推荐数据
      thirdData: [], //三级推荐
      fourthData: [], //四级推荐
      newBlogData: [], //最新文章
      hotBlogData: [], //最热文章
      hotTagData: [], //最新标签
      keyword: "",
      //查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderByColumn: "createTime",
        isAsc:"desc"
      },

      total: 0, //总数量
      isEnd: false, //是否到底底部了
      loading: false //是否正在加载
    }
  },
  created() {
    // 获取最新博客
    this.newBlogList();
  },
  mounted() {
    // 注册scroll事件并监听
    let that = this;
    that.loading = false;
  },
  methods:{
    /**  最新博客*/
    newBlogList(){
      let that = this;
      that.loadingInstance = Loading.service({
        lock: true,
        text: '正在努力加载中……',
        background: 'rgba(0, 0, 0, 0.7)'
      })

      getBlogList(this.queryParams).then(response => {
        that.newBlogData = response.rows;
        that.total = response.total;
        that.queryParams.pageNum = that.queryParams.pageNum+1;
        that.loadingInstance.close();
      });
    },
    //加载更多
    loadContent(){
      let that = this;
      that.loading = false;
      getBlogList(this.queryParams).then(response => {
        that.isEnd = false;
        var newData = that.newBlogData.concat(response.rows);
        that.newBlogData = newData;
        that.total = response.total;
        that.queryParams.pageNum = that.queryParams.pageNum+1;
        //全部加载完毕
        if (newData.length < that.pageSize) {
          that.isEnd = true;
        }
        that.loading = false;
      });
    },
    //跳转到文章详情
    goToInfo(blog) {
      let routeData = this.$router.resolve({
        path: "/info",
        query: {blogId: blog.id}
      });
      window.open(routeData.href, '_blank');
    }
  }
}
</script>

<style scoped>
.el-loading-mask {
  z-index: 2002;
}
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

.iconfont {
  font-size: 15px;
  margin-right: 2px;
}
</style>
