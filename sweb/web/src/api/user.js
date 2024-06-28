import http  from '../utils/request'

// 获取用户的数据
export const getUserList = (params) => {
    return http.get('/users/list', params)
}

// 修改用户的数据
export const updateUser = (params) => {
    return http.post('/users/updateUser', params)
}

// 删除用户的数据
export const deleteUser = (id) => {
    return http.post('/users/deleteUser/'+ id)
}

// 新增用户的数据
export const insertUser = (data) => {
    return http.post('/users/saveUser', data)
}