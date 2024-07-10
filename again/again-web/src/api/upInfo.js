import http  from '../utils/request'


export const getLoginUserInfo = () => {
  return http.get('/user/getLoginUserInfo')
}

export const updateUserInfo = (data) => {
  return http.post('/user/upUserInfo', data)
}

