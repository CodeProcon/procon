import request from '@/utils/request'

// 查询图片列表
export function listPicture(query) {
  return request({
    url: '/blog/picture/list',
    method: 'get',
    params: query
  })
}

// 查询图片详细
export function getPicture(id) {
  return request({
    url: '/blog/picture/' + id,
    method: 'get'
  })
}

// 新增图片
export function addPicture(data) {
  return request({
    url: '/blog/picture',
    method: 'post',
    data: data
  })
}

// 修改图片
export function updatePicture(data) {
  return request({
    url: '/blog/picture',
    method: 'put',
    data: data
  })
}

// 删除图片
export function delPicture(id) {
  return request({
    url: '/blog/picture/' + id,
    method: 'delete'
  })
}
