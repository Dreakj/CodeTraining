package com.atguigu.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-08-22-上午10:59
 */
public class TestVod {
    public static void main(String[] args) throws ClientException {
        String accessKeyId = "LTAI5t5wRnBdW8qzYoLseHq7";
        String accessKeySecret = "Fz1DDpf2NyJhKnkreffzIDhFuefpNZ";
        String title = "日本大片儿";
        String fileName = "/Users/liuziyue/Desktop/JAVA/在线教育--谷粒学院/项目资料/1-阿里云上传测试视频/6 - What If I Want to Move Faster.mp4";

        //实现上传视频的方法

        //本地文件上传
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId,
                accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        /* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
        request.setEnableCheckpoint(false);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
        /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    public static void getPlayAuth() throws ClientException {
        //根据视频id获取播放凭证
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5t5wRnBdW8qzYoLseHq7", "Fz1DDpf2NyJhKnkreffzIDhFuefpNZ");
        //创建获取视频凭证的request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        //向response中设置id
        request.setVideoId("27745081a65a4e01972a9a528a2e1383");
        //调用初始化对象方法获取凭证
        response = client.getAcsResponse(request);
        System.out.println(response.getPlayAuth());
    }

    public static void getPlayUrl() throws ClientException {
        //1.根据视频id获取视频的播放地址
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5t5wRnBdW8qzYoLseHq7", "Fz1DDpf2NyJhKnkreffzIDhFuefpNZ");
        //创建视频地址的request对象和response对象
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向request对象中设置视频id
        request.setVideoId("27745081a65a4e01972a9a528a2e1383");
        //调用初始化对象中的方法，传递request，获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            String s = playInfo.getPlayURL() + "\n";
            System.out.print("PlayInfo.PlayURL = " + s);
        }
        //base信息
        System.out.println("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
