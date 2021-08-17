// 根据字典类型查询列表
import request from "../../utils/request";

export function getListByDictTypeList(data) {
  return request({
    url: '/system/dict/data/dictTypeList',
    method: 'post',
    data: data
  })
}
