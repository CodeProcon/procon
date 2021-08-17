import request from "../../utils/request";

export function blogTagList(query){
  return request({
    url:'/blog/admin/blogTag/getList',
    method:'get',
    params:query
  })
}


// 查询标签详细
export function getBlogTag(id) {
  return request({
    url: '/blog/admin/blogTag/' + id,
    method: 'get'
  })
}

// 新增标签
export function addBlogTag(data) {
  return request({
    url: '/blog/admin/blogTag',
    method: 'post',
    data: data
  })
}

// 修改标签
export function updateBlogTag(data) {
  return request({
    url: '/blog/admin/blogTag',
    method: 'put',
    data: data
  })
}

// 删除标签
export function delBlogTag(id) {
  return request({
    url: '/blog/admin/blogTag/' + id,
    method: 'delete'
  })
}
