package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-07-25
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息
    @PostMapping("addCourseInfo")
    @ApiOperation(value = "新增课程")
    public R addCourseInfo(@ApiParam(name = "CourseInfoForm", value = "课程基本信息", required =
            true)
                           @RequestBody CourseInfoVo courseInfoVo) {

        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }
}

