package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
public interface EduTeacherService extends IService<EduTeacher> {
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);

}
