import request from '@/utils/request'

export function getWebConfig() {
  return request({
    url: '/blog/config/get',
    method: 'get'
  })
}
