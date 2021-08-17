<!--热门博客-->
<template>
  <div class="tuijian" v-if="hotBlogData.length > 0">
    <h2 class="hometitle">点击排行</h2>
    <ul class="tjpic" v-if="hotBlogData[0]">
      <i><img style="cursor:pointer" v-if="hotBlogData[0].photoList" :src="hotBlogData[0].photoList[0]" @click="goToInfo(hotBlogData[0])"></i>
      <p><a href="javascript:void(0);" @click="goToInfo(hotBlogData[0])">{{hotBlogData[0].title}}</a></p>
    </ul>
    <ul class="sidenews">
      <li v-for="(item, index) in hotBlogData" v-if="index != 0" :key="item.id">
        <i><img style="cursor:pointer"  v-if="item.photoList" :src="item.photoList[0]" @click="goToInfo(item)" ></i>
        <p><a href="javascript:void(0);" @click="goToInfo(item)">{{item.title}}</a></p>
        <span>{{item.createTime}}</span>
      </li>
    </ul>
  </div>
</template>

<script>
import {getBlogList,getBlogById} from "@/api/blog/home"
export default {
  name: "HotBlog",
  data(){
    return{
      //查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        orderByColumn: "clickCount",
        isAsc:"desc"
      },
      hotBlogData: [] //热门博客列表
    }
  },
  created() {

    this.getHotBlog();
  },
  methods:{
    /** 获取热门博客 */
    getHotBlog(){
      getBlogList(this.queryParams).then(response => {
        this.hotBlogData = response.rows;
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

</style>
