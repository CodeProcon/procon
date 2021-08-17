/**  主页接口*/
import request from '@/utils/request'

/**  获取网站配置*/
export function getWebConfig() {
  return request({
    url: '/blog/web/index/getWebConfig',
    method: 'get'
  })
}

/**  获取博客列表*/
export function getBlogList(query) {
  return request({
    url: '/blog/web/index/getBlogList',
    method: 'get',
    params:query
  })
}

export function getBlogById(id) {
  return request({
    url: '/blog/web/index/get/' + id,
    method: 'get'
  })
}

/** 增加博客点击数 */
export function addBlogClickCount(id) {
  return request({
    url: '/blog/web/index/addBlogClickCount/' + id,
    method: 'get'
  })
}


/**  获取标签列表*/
export function getTagList(query) {
  return request({
    url: '/blog/web/index/getTagList',
    method: 'get',
    params:query
  })
}

/**  获取友情链接列表*/
export function getLinkList(query) {
  return request({
    url: '/blog/web/index/getLinkList',
    method: 'get',
    params:query
  })
}
/**  增加链接点击数*/
export function addLinkClickCount(id) {
  return request({
    url: '/blog/web/index/addLinkClickCount/' + id,
    method: 'get'
  })
}
