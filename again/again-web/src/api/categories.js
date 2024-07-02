import http  from '../utils/request'

// 获取商品分类的数据
export const getCategoriesList = (params) => {
  return http.get('/category-tempalte/list', params)
}

// 修改商品分类的数据
export const updateCategories = (params) => {
  return http.post('/category-tempalte/update', params)
}

// 删除商品分类的数据
export const deleteCategories = (id) => {
  return http.post('/category-tempalte/delete/'+id)
}

// 新增商品分类的数据
export const insertCategories = (data) => {
  return http.post('/category-tempalte/insert', data)
}
