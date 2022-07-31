import request from '@/utils/request'
export default {
    //1.讲师列表（条件查询分页）
    getTeacherListPage(current, limit, teacherQuery) {
        return request({
            url: '/eduservice/teacher/pageTeacherCondition/' + current + "/" + limit,
            method: 'post',
            //teacherQuery条件对象，后端使用RequestBody获取数据
            //表示把teacherQuey转换成json传递到接口里面
            data: teacherQuery
        })
    },
    deleteTeacherId(id) {
        return request({
            url: '/eduservice/teacher/' + id,
            method: 'delete'
            //teacherQuery条件对象，后端使用RequestBody获取数据
            //表示把teacherQuey转换成json传递到接口里面
        })
    },
    //添加讲师
    addTeacher(teacher) {
        return request({
            url: '/eduservice/teacher/addTeacher',
            method: 'post',
            data: teacher
        })
    },
    //回显数据
    getTeacherInfo(id){
        return request({
            url:'/eduservice/teacher/getTeacher'+'/'+id,
            method:'get',
            // data: teacher,
        })
    },
    //修改老师数据
    updateTeacher(teacher){
        return request({
            url:'/eduservice/teacher/updateTeacher',
            method:'post',
            data:teacher
        })
    }
}
