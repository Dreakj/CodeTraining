package com.atguigu.eduservice.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-29-下午12:55
 */
@ApiModel(value = "课时信息")
@Data
public class VideoVo {
    private String id;
    private String title;
}
