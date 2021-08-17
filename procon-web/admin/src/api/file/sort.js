import request from '@/utils/request'

// 查询文件分类列表
export function listSort(query) {
  return request({
    url: '/file/sort/list',
    method: 'get',
    params: query
  })
}

// 查询文件分类详细
export function getSort(id) {
  return request({
    url: '/file/sort/' + id,
    method: 'get'
  })
}

// 新增文件分类
export function addSort(data) {
  return request({
    url: '/file/sort',
    method: 'post',
    data: data
  })
}

// 修改文件分类
export function updateSort(data) {
  return request({
    url: '/file/sort',
    method: 'put',
    data: data
  })
}

// 删除文件分类
export function delSort(id) {
  return request({
    url: '/file/sort/' + id,
    method: 'delete'
  })
}
