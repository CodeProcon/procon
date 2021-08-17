<template>
  <div class="tuijian" v-if="fourthData.length > 0">
    <h2 class="hometitle">推荐文章</h2>
    <ul class="tjpic" v-if="fourthData[0]">
      <i><img v-if="fourthData[0].photoList" style="cursor:pointer" @click="goToInfo(fourthData[0])"  :src="fourthData[0].photoList[0]"></i>
      <p><a href="javascript:void(0);" @click="goToInfo(fourthData[0])" >{{fourthData[0].title}}</a></p>
    </ul>

    <ul class="sidenews">
      <li v-for="(item, index) in fourthData" v-if="index != 0" :key="item.id">
        <i><img style="cursor:pointer" v-if="item.photoList"  :src="item.photoList[0]" @click="goToInfo(fourthData[0])" ></i>
        <p><a href="javascript:void(0);" @click="goToInfo(item)">{{item.title}}</a></p>
        <span>{{item.createTime}}</span>
      </li>
    </ul>
  </div>
</template>

<script>
import {getBlogList} from "@/api/blog/home";

export default {
  name: "FourRecommend",
  data(){
    return {
      //查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        orderByColumn: "sort",
        isAsc:"desc"
      },
      fourthData: [] //；四级推荐数据
    }
  },
  created() {
    this.getBlogByLevel(4)
  },
  methods:{
    getBlogByLevel(level){
      this.queryParams.level = level;
      getBlogList(this.queryParams).then(response => {
        this.fourthData = response.rows;
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

</style>
