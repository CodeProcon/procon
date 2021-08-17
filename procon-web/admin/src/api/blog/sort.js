import request from "../../utils/request";

export function blogSortList(query){
  return request({
    url:'/blog/admin/blogSort/getList',
    method:'get',
    params:query
    })
}

// 查询分类详细
export function getBlogSort(id) {
  return request({
    url: '/blog/admin/blogSort/' + id,
    method: 'get'
  })
}

// 新增分类
export function addBlogSort(data) {
  return request({
    url: '/blog/admin/blogSort',
    method: 'post',
    data: data
  })
}

// 修改图片分类
export function updateBlogSort(data) {
  return request({
    url: '/blog/admin/blogSort',
    method: 'put',
    data: data
  })
}

// 删除图片分类
export function delBlogSort(id) {
  return request({
    url: '/blog/admin/blogSort/' + id,
    method: 'delete'
  })
}
