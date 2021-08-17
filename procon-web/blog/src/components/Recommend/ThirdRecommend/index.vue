<template>
  <div class="zhuanti" v-if="thirdData.length > 0">
    <h2 class="hometitle">特别推荐</h2>
    <ul>
      <li  v-for="item in thirdData" style="cursor: pointer" :key="item.id" @click="goToInfo(item)">
        <i><img v-if="item.photoList" :src="item.photoList[0]"></i>
        <p>{{splitStr(item.title, 30)}}<span><a href="javascript:void(0);" >阅读</a></span> </p>
      </li>
    </ul>
  </div>
</template>

<script>
import {getBlogList} from "@/api/blog/home";

export default {
  name: "ThirdRecommend",
  data(){
    return {
      //查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        orderByColumn: "sort",
        isAsc:"desc"
      },
      thirdData: [], // 三级推荐数据
    }
  },
  created() {
    this.getBlogByLevel(3);
  },
  methods:{
    getBlogByLevel(level){
      this.queryParams.level = level;
      getBlogList(this.queryParams).then(response => {
        this.thirdData = response.rows;
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
    splitStr(str, flagCount) {
      return this.$commonUtil.splitStr(str, flagCount)
    }
  }
}
</script>

<style scoped>

</style>
