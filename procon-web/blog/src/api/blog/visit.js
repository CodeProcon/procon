import request from '@/utils/request'

// 查询Web访问记录列表
export function listVisit(query) {
  return request({
    url: '/blog/visit/list',
    method: 'get',
    params: query
  })
}

// 查询Web访问记录详细
export function getVisit(id) {
  return request({
    url: '/blog/visit/' + id,
    method: 'get'
  })
}

// 新增Web访问记录
export function addVisit(data) {
  return request({
    url: '/blog/visit',
    method: 'post',
    data: data
  })
}

// 修改Web访问记录
export function updateVisit(data) {
  return request({
    url: '/blog/visit',
    method: 'put',
    data: data
  })
}

// 删除Web访问记录
export function delVisit(id) {
  return request({
    url: '/blog/visit/' + id,
    method: 'delete'
  })
}
