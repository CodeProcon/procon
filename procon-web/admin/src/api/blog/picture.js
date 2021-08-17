import request from "../../utils/request";

export function getPictureList(query){
  return request({
    url:'/blog/pic/getList',
    method:'get',
    params:query
  })
}
