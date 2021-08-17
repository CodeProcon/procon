/**  归档相关接口*/

import request from "../../utils/request";

/**  获取归档列表*/
export function getArchiveList(query) {
  return request({
    url: '/blog/web/archive/getArchiveList',
    method: 'get',
    params: query
  })
}

export function getArticleByMonth(query) {
  return request({
    url: '/blog/web/archive/getArticleByMonth',
    method: 'get',
    params: query
  })
}
