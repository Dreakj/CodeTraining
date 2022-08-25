package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-08-23-上午10:32
 */
public interface VodService {
    String uploadVideoAly(MultipartFile file);
}
