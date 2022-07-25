package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-23-上午10:39
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }
        //添加一级分类
        //判断一级分类是否重复
        EduSubject exitOneSubject = this.exitsOneSubject(this.subjectService, subjectData.getOneSubject());
        if (exitOneSubject == null) {//没有相同一级分类
            exitOneSubject = new EduSubject();
            exitOneSubject.setParentId("0");
            exitOneSubject.setTitle(subjectData.getOneSubject());
            subjectService.save(exitOneSubject);
        }
        //添加二级分类
        EduSubject exitTwoSubject = this.exitsTwoSubject(this.subjectService, subjectData.getTwoSubject(), exitOneSubject.getId());
        if (exitTwoSubject == null) {
            //没有二级分类
            exitTwoSubject = new EduSubject();
            exitTwoSubject.setParentId(exitOneSubject.getId());
            exitTwoSubject.setTitle(subjectData.getTwoSubject());
            subjectService.save(exitTwoSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //判断一级分类不能重复添加
    private EduSubject exitsOneSubject(EduSubjectService eduSubjectService, String title) {
        QueryWrapper<EduSubject> eduSubjectQueryWrapper = new QueryWrapper<>();
        eduSubjectQueryWrapper.eq("title", title);
        eduSubjectQueryWrapper.eq("parent_id", "0");
        EduSubject one = eduSubjectService.getOne(eduSubjectQueryWrapper);
        return one;
    }

    //判断二级分类不能重复添加
    private EduSubject exitsTwoSubject(EduSubjectService eduSubjectService, String title, String id) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", id);
        EduSubject one = eduSubjectService.getOne(queryWrapper);
        return one;
    }

}
