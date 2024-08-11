import http  from '../utils/request'

// 获取全部商品数据
export const dynamicTable = (params) => {
  return http.get('/dynamicTable/list',params)
}
