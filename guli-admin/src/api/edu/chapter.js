import request from '@/utils/request'
export default {
    //根据课程id获取章节和小节
    getAllChapterVideo(courseId) {
        return request({
            url: '/eduservice/chapter/getChapterVideo/' + courseId,
            method: 'get',
        })
    },
    //添加章节
    addChapter(chapter) {
        return request({
            url: '/eduservice/chapter/addChapter',
            method: 'post',
            data: chapter
        })
    },
    //根据章节id查询
    getChapterById(id) {
        return request({
            url: '/eduservice/chapter/getChapterInfo/' + id,
            method: 'get',
        })
    },
    //修改章节
    updateChapter(chapter){
        return request({
            url:'/eduservice/chapter/update',
            method:'post',
            data: chapter,
        })
    },
    //删除章节
    deleteChapter(id){
        return request({
            url:'/eduservice/chapter/'+id,
            method:'delete',
        })
    }
}