package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-07-25
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程id查询课程大纲列表
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String id);

    void removeChapterByCourseId(String id);
}
