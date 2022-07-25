package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-19-下午11:09
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    //上传头像
    @ApiOperation("头像上传")
    @PostMapping
    public R uploadOssFile(@ApiParam(name = "file", value = "文件", required = true)
                       @RequestParam("file") MultipartFile file) {
        String url = ossService.uploadFileAvatar(file);
        return R.ok().message("文件上传成功").data("url", url);
    }
}
