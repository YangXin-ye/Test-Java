import http  from '../utils/request'

// 获取全部商品数据
export const getProductList = (params) => {
  return http.get('/products/list',params)
}

// 修改商品数据
export const updateProduct = (data) => {
  return http.post('/products/update', data)
}

// 新增商品
export const addProduct = (data) => {
  return http.post('/products/add', data)
}

// 根据id删除商品
export const deleteProduct = (id) => {
  return http.post('/products/delete/' + id )
}

// 获取标签数据
export const getTagList = () => {
  return http.get('/category-tempalte/categoryName')
}
