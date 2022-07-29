package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-07-25
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    List<ChapterVo> finalList = new ArrayList<>();
    @Autowired
    private EduVideoService videoService;
    //根据课程id查询课程大纲列表
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id查询课程里面的所有章节
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id", courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(queryWrapper);
        //2.查询小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);
        //3.遍利查询章节list集合进行封装
        int count = eduChapters.size();
        for (int i = 0; i < count; i++) {
            EduChapter eduChapter = eduChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);
            int count2 = eduVideoList.size();
            //封装章节中的小节
            List<VideoVo> videoVoList = new ArrayList<>();
            //4.遍历查询小节list集合进行封装
            for (int j = 0; j < count2; j++) {
                EduVideo eduVideo = eduVideoList.get(j);
                //判断小节中的chapter_id是否和章节中的id相同
                if (eduVideo.getChapterId().equals(chapterVo.getId())) {
                    VideoVo eduVideo1 = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, eduVideo1);
                    videoVoList.add(eduVideo1);
                }

            }
            chapterVo.setChildren(videoVoList);
        }

        return finalList;
    }
}
