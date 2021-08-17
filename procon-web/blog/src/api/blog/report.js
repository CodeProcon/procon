import request from '@/utils/request'

// 查询评论举报列表
export function listReport(query) {
  return request({
    url: '/blog/report/list',
    method: 'get',
    params: query
  })
}

// 查询评论举报详细
export function getReport(id) {
  return request({
    url: '/blog/report/' + id,
    method: 'get'
  })
}

// 新增评论举报
export function addReport(data) {
  return request({
    url: '/blog/report',
    method: 'post',
    data: data
  })
}

// 修改评论举报
export function updateReport(data) {
  return request({
    url: '/blog/report',
    method: 'put',
    data: data
  })
}

// 删除评论举报
export function delReport(id) {
  return request({
    url: '/blog/report/' + id,
    method: 'delete'
  })
}
