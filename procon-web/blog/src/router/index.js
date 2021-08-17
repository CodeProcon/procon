import Vue from 'vue'
import VueRouter from 'vue-router'
import home from '../views/home.vue'


Vue.use(VueRouter)

export const routes = [
  {
    path: '/',
    component: home,
    children:[
      { path: '/', component: () => import('@/views/index') },
      { path: '/about', component: () => import('@/views/about') },
      { path: '/list', component: () => import('@/views/list') },
      { path: '/info', component: () => import('@/views/info') },
      { path: '/sort', component: () => import('@/views/sort') },
      { path: '/classify', component: () => import('@/views/classify') },
      { path: '/tag', component: () => import('@/views/tag') },
    ]
  },
  { path: '/404', component: () => import('@/views/404') },
  { path: '/500', component: () => import('@/views/500') },
  { path: '/502', component: () => import('@/views/502') },
  { path: '/*', component: () => import('@/views/404') }
]

export default new VueRouter({
  base: "/blog", //假设子目录是 admin 可以替换为自己的
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: routes
})
// const router = new VueRouter({
//   routes
// })

//export default router
