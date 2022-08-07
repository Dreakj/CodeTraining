package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-08-07-下午9:07
 */
@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;

}
