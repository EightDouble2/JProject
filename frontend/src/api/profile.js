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

export function modifyPassword(data) {
  return request({
    url: '/profile/modify/password',
    method: 'post',
    data
  })
}

export function modifyIcon(data) {
  return request({
    url: '/profile/modify/icon',
    method: 'post',
    data
  })
}
