<template>
  <head>
    <meta charset="utf-8">
    <title>{{info.title}}</title>
    <meta name="keywords" :content="info.keyword">
    <meta name="description" :content="info.summary">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
</template>

<script>
import {getWebConfig} from "@/api/blog/home";

export default {
  name: "head",
  data(){
    return{
      info:{}
    }
  },
  created() {
    //获取系统配置信息
   getWebConfig().then(response => {
      this.info = response.data;
    });
  },
  methods:{

  }
}
</script>

<style scoped>

</style>
