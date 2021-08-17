## 开发

```bash
# 克隆项目
git clone https://gitee.com/y_project/procon-Vue

# 进入项目目录
cd procon-ui

# 安装依赖
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# 启动服务
npm run dev
```

浏览器访问 http://localhost:80

## 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
```

## 如果需要部署子目录比如
```
https:www.procon.vip/admin
1.修改vue.config.js中的publicPath属性


publicPath: process.env.NODE_ENV === "production" ? "/admin/" : "/admin/",
2、修改router/index.js，添加一行base属性

export default new Router({
base: "/admin", //假设子目录是 admin 可以替换为自己的
mode: 'history', // 去掉url中的#
scrollBehavior: () => ({ y: 0 }),
routes: constantRoutes
})
3、修改layout/components/Navbar.vue中的location.href

location.href = this.$router.options.base + '/index';
```