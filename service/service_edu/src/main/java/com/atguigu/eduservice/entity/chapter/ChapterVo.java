package com.atguigu.eduservice.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-29-下午12:55
 */
@ApiModel(value = "章节信息")
@Data
public class ChapterVo {
    private String id;
    private String title;
    //小节
    private List<VideoVo> children = new ArrayList<>();

}
