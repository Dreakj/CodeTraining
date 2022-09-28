package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.entity.frontvo.UcenterMemberPay;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-09-20
 */
public interface EduCommentService extends IService<EduComment> {
    UcenterMemberPay getInfo(@PathVariable String id);

}
