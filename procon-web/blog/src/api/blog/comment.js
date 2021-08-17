/**  评论相关接口*/
import request from '@/utils/request'

// 查询评论列表
export function getCommentList(query) {
  return request({
    url: '/blog/web/comment/getList',
    method: 'get',
    params: query
  })
}

// 举报评论
export function reportComment(data) {
  return request({
    url: '/blog/authWeb/blog/comment/report',
    method: 'post',
    data:data
  })
}

// 新增评论
export function addComment(data) {
  return request({
    url: '/blog/authWeb/blog/comment/add',
    method: 'post',
    data: data
  })
}


// 删除评论
export function deleteComment(data) {
  return request({
    url: '/blog/authWeb/blog/comment/delete',
    method: 'delete',
    data: data
  })
}
