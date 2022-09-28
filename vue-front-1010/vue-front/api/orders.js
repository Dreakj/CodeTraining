import request from '@/utils/request'
export default{
    //创建订单
    createOrders(courseId) {
        return request({
            url:'/orderservice/ordercreateOrder/'+courseId,
            method:'post',

        })
    },
    //2、根据id获取订单信息
    getOrdersInfo(id) {
        return request({
            url:'/orderservice/order/getOrder/' + id,
            method:'get',
        })
    },
    //3、生成微信支付二维码
    createNative(cid){
        return request({
            url:'/orderservice/tpaylog//createNative/'+cid,
            method:'get',
        })
    },
    // 4、根据id获取订单支付状态
    queryPayStatus(cid){
        return request({
            url:'/orderservice/tpaylog/queryPayStatus/'+cid,
            method:'get',
        })
    }
}