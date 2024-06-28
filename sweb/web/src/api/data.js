import http  from '../utils/request'

// 获取用户的详细数据
export const getData = (params) => {
    return http.get('/orders/list', params)
}

// 修改用户的数据
export const updateData = (params) => {
    return http.put('/orders/update', params)
}

// 删除用户的数据
export const deleteData = (id) => {
    return http.delete('/orders/delete/', id)
}

// 新增用户的数据
export const insertData = (data) => {
    return http.post('/orders/insert', data)
}

// 获取省市区的数据
export const getDataProvinceAndCityAndArea = (params) => {
    return http.get('/district/listDistrict', params)
}   