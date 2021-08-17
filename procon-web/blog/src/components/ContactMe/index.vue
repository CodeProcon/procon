<template>
  <div class="guanzhu" id="follow-us" ref="follow">
    <h2 class="hometitle">关注我 么么哒！</h2>
    <ul>
      <li class="qq" v-if="contact.qqNumber"><a href="tencent://AddContact/?fromId=50&fromSubId=1&subcmd=all&uin=1595833114" target="_blank"><span>QQ号</span>{{contact.qqNumber}}</a></li>
      <li class="email" v-if="contact.email"><a :href="mailto" target="_blank"><span>邮箱帐号</span>{{contact.email}}</a></li>
      <li class="wxgzh" v-if="contact.weChat"><a href="/" target="_blank"><span>微信号</span>{{contact.weChat}}</a></li>
      <li class="github" v-if="contact.github"><a :href="contact.github" target="_blank"><span>Github</span>{{contact.github}}</a></li>
      <li class="gitee" v-if="contact.gitee"><a :href="contact.gitee" target="_blank"><span>Gitee</span>{{contact.gitee}}</a></li>
    </ul>
  </div>
</template>

<script>
import {getWebConfig} from "@/api/blog/home";

export default {
  name: "ContactMe",
  data(){
    return{
      contact: {},
      mailto: ""
    }
  },
  created() {
    this.getContactData()
  },
  methods:{
    getContactData:function (){
      // TODO 存入vuex中
      //获取系统配置信息
      getWebConfig().then(response => {
        this.contact = response.data;
        this.mailto = "mailto:" + this.contact.mailto;

      });
    }
  }
}
</script>

<style scoped>

</style>
