import http  from '../utils/request'

// 同步数据
export const getData = (params) => {
  return http.get('/es/list', params)
}


export const syncData = () => {
  return http.get('/es/syncData')
}
