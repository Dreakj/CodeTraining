package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-19-下午11:10
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        OSS ossClient = null;
        String url = null;
        try {
            //判断oss实例是否存在，不存在则创建，存在则获取
            ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");
            //获取文件上传流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //在文件名称中添加随机的唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + fileName;
            //把文件按照日期进行分类，首先获取当前日期
            Date date = new Date();
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            fileName = datePath + "/" + fileName;

            //调用oss实现上传
            ossClient.putObject(bucketName, fileName, inputStream);
            //oss路径拼接
             url = "https://" + bucketName + "." + endPoint + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return url;
    }
}
