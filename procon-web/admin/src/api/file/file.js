import request from '@/utils/request'

// 查询文件列表
export function listFile(query) {
  return request({
    url: '/file/file/list',
    method: 'get',
    params: query
  })
}

// 查询文件详细
export function getFile(id) {
  return request({
    url: '/file/file/' + id,
    method: 'get'
  })
}

// 新增文件
export function addFile(data) {
  return request({
    url: '/file/file/add',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

export function saveFile(data) {
  return request({
    url: '/file/file/save',
    method: 'post',
    data: data
  })
}

// 修改文件
export function updateFile(data) {
  return request({
    url: '/file/file',
    method: 'put',
    data: data
  })
}

// 删除文件
export function delFile(id) {
  return request({
    url: '/file/file/' + id,
    method: 'delete'
  })
}
