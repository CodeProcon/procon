<template>
  <div class="banner">
    <el-carousel class="bannerBox" indicator-position="outside" >
      <el-carousel-item  v-for="(list,index) in slideList" :key="index">
        <img
          style="width:100%; height:100%; display:block;cursor:pointer;"
          v-if="list.photoList"
          :src="list.photoList[0]"
          :alt="list.title"
          @click="goToInfo(list)">
        <div class="carousel-title" @click="goToInfo(list)">
          <span>{{list.title}}</span>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
import {getBlogList} from "@/api/blog/home";

export default {
  name: "FirstRecommend",
  data(){
    return{
      //查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderByColumn: "sort",
        isAsc:"desc"
      },
      isShow: false, //控制左右滑动按钮是否显示
      slideList: [],
      currentIndex: 0,
      timer: "",
      firstData: [] //；一级推荐数据
    }
  },
  created() {
    //获取一级推荐数据
    this.getBlogByLevel(1);
  },
  methods:{

    getBlogByLevel(level){
      this.queryParams.level = level;
      getBlogList(this.queryParams).then(response => {
        this.firstData = response.rows;
        this.slideList = this.firstData;
      });
    },
    //跳转到文章详情
    goToInfo(blog) {
      let routeData = this.$router.resolve({
        path: "/info",
        query: {blogId: blog.id}
      });
      window.open(routeData.href, '_blank');
    },
  }

}
</script>

<style scoped>
.el-carousel__container {
  height: 450px !important;;
}
.carousel-title span {
  color: white;
  font-size: 22px;
  display: inline-block;
}

@media only screen and (max-width: 1200px) {
  .el-carousel__container {
    height: 360px !important;;
  }
}
@media only screen and (max-width: 1000px) {
  .el-carousel__container {
    height: 340px !important;;
  }
}

@media only screen and (max-width: 960px) {
  .el-carousel__container {
    height: 280px !important;;
  }
}

@media only screen and (max-width: 500px) {
  .el-carousel__container {
    height: 200px !important;;
  }
}

.carousel-title {
  cursor: pointer;
  position: absolute;
  z-index: 10;
  bottom: 40px;
  height: 40px;
  width: 100%;
  line-height: 40px;
  text-align: center;
  background: rgba(0, 0, 0, 0.3);
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
