// 查询博客列表
import request from "@/utils/request";

export function getConfigList(query) {
  return request({
    url: '/blog/admin/config/list',
    method: 'GET',
    params: query
  })
}

// 添加博客
export function addBlog(data) {
  return request({
    url: '/blog/admin/config',
    method: 'post',
    data: data
  })
}

// 修改博客
export function updateConfig(data) {
  return request({
    url: '/blog/admin/config',
    method: 'put',
    data: data
  })
}
