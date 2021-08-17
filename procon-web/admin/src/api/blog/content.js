
import request from "@/utils/request";

// 查询博客列表
export function listBlog(query) {
  return request({
    url: '/blog/admin/blogMgr/getList',
    method: 'GET',
    params: query
  })
}

// 添加博客
export function addBlog(data) {
  return request({
    url: '/blog/admin/blogMgr/add',
    method: 'post',
    data: data
  })
}

// 修改博客
export function updateContent(data) {
  return request({
    url: '/blog/admin/blogMgr/update',
    method: 'post',
    data: data
  })
}

// 删除博客
export function delContent(id) {
  return request({
    url: '/blog/admin/blogMgr/' + id,
    method: 'delete'
  })
}

// 查询博客详细
export function getContent(id) {
  return request({
    url: '/blog/admin/blogMgr/get/' + id,
    method: 'get'
  })
}
//文件上传
export function fileUpload(data) {
  return request({
    url: '/file/file/add',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

/** 批量修改 */
export function editBatchBlog(data) {
  return request({
    url: '/blog/admin/blogMgr/editBatch',
    method: 'post',
    data: data
  })
}
