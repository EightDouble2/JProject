import request from '@/utils/request'

export function info(username) {
  return request({
    url: '/profile/info/' + username,
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: '/profile/update',
    method: 'post',
    data
  })
}
