import http  from '../utils/request'

// 获取用户的数据
export const getCategoriesList = (params) => {
    return http.get('/category-tempalte/list', params)
}

// 修改用户的数据
export const updateCategories = (params) => {
    return http.put('/category-tempalte/update', params)
}

// 删除用户的数据
export const deleteCategories = (id) => {
    return http.delete('/category-tempalte/delete/', id)
}

// 新增用户的数据
export const insertCategories = (data) => {
    return http.post('/category-tempalte/insert', data)
}