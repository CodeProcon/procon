/**  博客信息接口*/
import request from '@/utils/request'

// 查询博客列表
export function listContent(query) {
  return request({
    url: '/blog/content/list',
    method: 'get',
    params: query
  })
}

/**  根据id查博客*/
export function getBlogById(id) {
  return request({
    url: '/blog/web/blogInfo/get/' + id,
    method: 'get'
  })
}

/**  根据id查相似博客*/
export function getSameBlogByBlogId(id) {
  return request({
    url: '/blog/web/blogInfo/getSameBlog/' + id,
    method: 'get'
  })
}


export function getBlogPraiseCount(id) {
  return request({
    url: '/blog/web/blogInfo/getBlogPraiseCount/' + id,
    method: 'get'
  })
}

/**  给博客点赞*/
export function praiseBlogById(id) {
  return request({
    url: '/blog/authWeb/blog/praiseBlogById/' + id,
    method: 'get'
  })
}
