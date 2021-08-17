<!--标签列表-->
<template>
  <div class="cloud" v-if="hotTagData.length > 0">
    <h2 class="hometitle">标签列表</h2>
    <ul>
      <a v-for="item in hotTagData" :key="item.id" href="javascript:void(0);" >{{item.content}}</a>
    </ul>
  </div>
</template>

<script>
import {getTagList} from "@/api/blog/home";

export default {
  name: "Tags",
  data(){
    return{
      //查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 20,
        orderByColumn: "sort",
        isAsc:"desc"
      },
      hotTagData: [] //标签列表
    }
  },
  created() {
      this.getHotTag();
  },
  methods:{
      getHotTag(){
        getTagList(this.queryParams).then(response => {
          this.hotTagData = response.rows;
        })
      },
    //跳转到搜索详情页
    goToList(uid) {
      let routeData = this.$router.resolve({ path: "/list", query: { tagId: uid } });
      window.open(routeData.href, '_blank');
    }
  }
}
</script>

<style scoped>

</style>
