import request from "../../utils/request";

export function getPictureSortList(query){
  return request({
    url:'/blog/picSort/getList',
    method:'get',
    params:query
  })
}

export function getPictureSortById(id) {
  return request({
    url: '/blog/picSort/get/' + id,
    method: 'get'
  })
}
