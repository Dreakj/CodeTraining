package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    //注入service的数据
    @Autowired
    private EduTeacherService eduTeacherService;

    //1.查询讲师表中所有数据
    //rest风格
    @ApiOperation("展示所有讲师")
    @GetMapping("findAll")
    public R findAllTeacher() {
        //调用service的方法实现查询所有的操作
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        List<EduTeacher> list = eduTeacherService.list(wrapper);
        return R.ok().data("items", list);
    }

    //2.根据id删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //3.分页查询讲师的方法
    @ApiOperation("分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable Long current, @PathVariable Long limit) {
        //1.创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //2.调用方法实现分页
        eduTeacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords();//每页数据的list集合
        Map<String, Object> map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }

    //4.条件查询带分页
    @ApiOperation("条件查询讲师并将结果进行分类")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable Long current, @PathVariable Long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
        //1.创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //2.构建条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空开始拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            //模糊查询
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");
        //3.调用service层方法实现分页查询
        eduTeacherService.page(pageTeacher, queryWrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("records", records);
        return R.ok().data(map);
    }

    //添加讲师接口方法
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher teacher) {
        boolean save = eduTeacherService.save(teacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据讲师id进行查找
    @ApiOperation(value = "根据id查找讲师讲师信息")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

