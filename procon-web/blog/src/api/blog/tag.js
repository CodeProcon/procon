import request from '@/utils/request'

// 查询标签列表
export function getTagList(query) {
  return request({
    url: 'blog/web/tag/getList',
    method: 'get',
    params: query
  })
}

/**  获取博客列表*/
export function getArticleByTagId(query) {
  return request({
    url: '/blog/web/tag/getBlogList',
    method: 'get',
    params: query
  })
}
