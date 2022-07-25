package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-24-下午12:30
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    //一个一级分类里有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
    private String name;
}
