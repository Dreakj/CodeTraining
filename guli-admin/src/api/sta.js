import request from '@/utils/request'

export default {
    createStatistics(day) {
        return request({
            url:'/staservice/sta/regosterCount/'+day,
            method:'post',
        })
    }
}