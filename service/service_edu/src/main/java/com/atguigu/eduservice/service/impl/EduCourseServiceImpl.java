package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-25
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    //添加课程信息
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {//添加失败
            throw new GuliException(20001, "添加课程信息失败");
        }
        String cid = eduCourse.getId();
        //2.向课程简介表中添加信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        //设置描述id就是课程id
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if (!save) {
            throw new GuliException(20001, "课程详情信息保存失败");
        }
        return cid;
    }
}
