<!-- 友情链接组件-->
<template>
  <div class="links" v-if="linkData.length > 0">
    <h2 class="hometitle">友情链接</h2>
    <ul>
      <li v-for="item in linkData" :key="item.id"><a  href="javascript:void(0);" @click="goLink(item)">{{item.title}}</a></li>
    </ul>
  </div>
</template>

<script>
import {getLinkList, addLinkClickCount, getBlogList} from "@/api/blog/home"
export default {
  name: "FriendlyLink",
  data(){
    return{
      //查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        orderByColumn: "clickCount",
        isAsc:"desc"
      },
      linkData:[]
    }
  },
  created() {
    //获取链接数据
    this.getFriendlyLink();
  },
  methods:{
    /** 获取热门博客 */
    getFriendlyLink(){
      getLinkList(this.queryParams).then(response => {
        this.linkData = response.rows;
      });
    },
    goLink(item) {
      addLinkClickCount(item.id).then(response => {
        window.open(item.url, '_blank').location;
      });
    }
  }
}
</script>

<style scoped>

</style>
