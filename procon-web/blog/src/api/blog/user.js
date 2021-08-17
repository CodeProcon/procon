import request from '@/utils/request'

/**  第三方登录*/
export function authLogin(source) {
  return request({
    url: '/auth/justAuth/render/' + source,
    method: 'get'
  })
}

/**  用户认证*/
export function authVerify(accessToken) {
  return request({
    url: '/auth/justAuth/verify/' + accessToken,
    method: 'get'
  })
}
