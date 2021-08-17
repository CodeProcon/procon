import request from '@/utils/request'

// 查询游客列表
export function listVisitor(query) {
  return request({
    url: '/blog/visitor/list',
    method: 'get',
    params: query
  })
}

// 查询游客详细
export function getVisitor(id) {
  return request({
    url: '/blog/visitor/' + id,
    method: 'get'
  })
}

// 新增游客
export function addVisitor(data) {
  return request({
    url: '/blog/visitor',
    method: 'post',
    data: data
  })
}

// 修改游客
export function updateVisitor(data) {
  return request({
    url: '/blog/visitor',
    method: 'put',
    data: data
  })
}

// 删除游客
export function delVisitor(id) {
  return request({
    url: '/blog/visitor/' + id,
    method: 'delete'
  })
}
