import http  from '../utils/request'

// 登录
export const login = (data) => {
    return http.post('/login',data)
}
// 获取用户信息
export const getUserInfo = () => {
    return http.get('/users/userInfo')
}