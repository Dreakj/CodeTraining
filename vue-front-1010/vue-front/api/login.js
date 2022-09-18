import request from '@/utils/request'
export default {
    //登录
    submitLogin(userInfo) {
        return request({
            url: '/educenter/member/login',
            method: 'post',
            data: userInfo
        })
    },
    getLoginUserInfo() {
        return request({
            url: '/educenter/member/getMemberInfo',
            method: 'get',
            // headers: {'token': cookie.get('guli_token')}
        })
        //headers: {'token': cookie.get('guli_token')}
    },
}