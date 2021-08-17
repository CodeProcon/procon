import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 引入公共JS
import "@/assets/iconfont/iconfont.css";
import '../public/css/ckeditor.css';
import '../public/css/index.css';
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n
import hljs from 'highlight.js' //导入代码高亮文件
import 'highlight.js/styles/monokai-sublime.css'  //导入代码高亮样式
Vue.use(ElementUI, { locale })
Vue.config.productionTip = false
import xss from 'xss'
// 定义全局XSS解决方法
Object.defineProperty(Vue.prototype, '$xss', {
  value: xss
})

// 引入全局工具类
import prototype from './utils/prototype'
Vue.use(prototype)

//自定义一个代码高亮指令
Vue.directive('highlight',function (el) {
  let highlight = el.querySelectorAll('pre code');
  highlight.forEach((block)=>{
    hljs.highlightBlock(block)
  })
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
