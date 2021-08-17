<template>
  <div class="toppic">
    <li v-for="item in secondData" :key="item.id" @click="goToInfo(item)">
      <a href="javascript:void(0);">
        <i>
          <img v-if="item.photoList" :src="item.photoList[0]">
        </i>
        <h2>{{item.title}}</h2>
        <span>{{item.blogSortList[0].sortName}}</span>
      </a>
    </li>
  </div>
</template>

<script>
import {getBlogList} from "@/api/blog/home";

export default {
  name: "secondRecommend",
  data(){
    return {
      //查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 2,
        orderByColumn: "sort",
        isAsc:"desc"
      },
      secondData: [] //；二级级推荐数据
    }
  },
  created() {
    this.getBlogByLevel(2);
  },
  methods:{
    getBlogByLevel(level){
      this.queryParams.level = level;
      getBlogList(this.queryParams).then(response => {
        this.secondData = response.rows;
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
