import request from '@/utils/request'
export default{
    //查询前两条Banner的数据
    getListBanner(){
        return request({
            url:'/educms/bannerfront/getAllBanner',
            method:'get'
        })
    }
}