import request from '@/utils/request'
export default{
    //1.添加小节
    addVideo(video){
        return request({
            url:'/eduservice/video/addVideo',
            method:'post',
            data:video,
        })
    },
    //2.修改小节
    updateVideo(video){
        return request({
            url:'/eduservice/video/updateVideo',
            method:'post',
            data:video,
        })
    },
    //3.删除小节
    deleteVideo(id){
        return request({
            url:'eduservice/video/'+ id,
            method:'delete',
        })
    },
    //4.查找小节
    getVideoById(id){
        return request({
            url:'eduservice/video/getVideoInfo/'+id,
            method:'get',
        })
    },
}