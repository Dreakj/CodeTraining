import request from '@/utils/request'
export default{
    //条件分页查询
    getCourseList(page,limit,searchObj) {
        return request({
            url:'/eduservice/coursefront/'+page +"/"+ limit,
            method:'post',
            data:searchObj,
        })
    },
    //查询所有分类
    getAllSubject(){
        return request({
            url:'eduservice/subject/getAllSubject',
            method:'get',
            
        })
    },
    getCourseInfo(id) {
        return request({
            url:'/eduservice/coursefront/getFrontCourseInfo/' + id,
            method:'get'
        })
    }
}