import request from "@/utils/request";

/**  获取分类列表*/
export function getBlogSortList(query) {
  return request({
    url: '/blog/web/classify/getBlogSortList',
    method: 'get',
    params: query
  })
}

/**  获取博客列表*/
export function getArticleByBlogSortId(query) {
  return request({
    url: '/blog/web/index/getBlogList',
    method: 'get',
    params: query
  })
}
