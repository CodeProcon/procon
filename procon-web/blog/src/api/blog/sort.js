import request from '@/utils/request'

// 查询分类列表
export function getSortList(query) {
  return request({
    url: '/blog/sort/list',
    method: 'get',
    params: query
  })
}

// 查询图片分类详细
export function getSort(id) {
  return request({
    url: '/blog/sort/' + id,
    method: 'get'
  })
}

// 新增图片分类
export function addSort(data) {
  return request({
    url: '/blog/sort',
    method: 'post',
    data: data
  })
}

// 修改图片分类
export function updateSort(data) {
  return request({
    url: '/blog/sort',
    method: 'put',
    data: data
  })
}

// 删除图片分类
export function delSort(id) {
  return request({
    url: '/blog/sort/' + id,
    method: 'delete'
  })
}
