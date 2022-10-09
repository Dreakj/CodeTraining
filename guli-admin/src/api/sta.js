import request from '@/utils/request'

export default {
    createStatistics(day) {
        return request({
            url:'/staservice/sta/registerCount/'+day,
            method:'post',
        })
    },
    showChart(searchObj) {
        return request({
            url:'/staservice/sta/showData/'+ searchObj.begin+'/'+searchObj.start+'/'+searchObj.end,
            method:'get',
        })
    }
}