import Cookies from 'js-cookie'

const TokenKey = 'Blog-Token'


export function getBlogToken() {
  return Cookies.get(TokenKey)
}

export function setBlogToken(token,expireTime) {
  return Cookies.set(TokenKey, token,{expires:expireTime})
}

export function removeBlogToken() {
  return Cookies.remove(TokenKey)
}



