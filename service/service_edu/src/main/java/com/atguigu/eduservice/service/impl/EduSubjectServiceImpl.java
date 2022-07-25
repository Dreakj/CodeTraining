package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-23
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    InputStream in;

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //文件输入流
            in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //最终要得到的类别集合
        List<OneSubject> finalSubjectList = new ArrayList<>();
        //1.查询所有一级分类
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(queryWrapper);

        //2.查询所有二级分类
        QueryWrapper<EduSubject> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(queryWrapper1);
        //3.封装一级分类
        int count = oneSubjectList.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            oneSubject.setId(subject.getId());
            oneSubject.setTitle(subject.getTitle());
            finalSubjectList.add(oneSubject);
            //4.封装二级分类
            int count2 = twoSubjectList.size();
            List<TwoSubject> twoSubjectList1 = new ArrayList<>();
            for (int j = 0; j < count2; j++) {
                EduSubject Subsubject = twoSubjectList.get(j);

                if (Subsubject.getParentId().equals(subject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    twoSubject.setId(Subsubject.getId());
                    twoSubject.setTitle(Subsubject.getTitle());
                    twoSubjectList1.add(twoSubject);
                }

            }
            oneSubject.setChildren(twoSubjectList1);
//            finalSubjectList.add(oneSubject);
        }

        return finalSubjectList;
    }
}
