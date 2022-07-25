package com.atguigu.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-19-下午11:08
 */

public interface OssService {
    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);
}
