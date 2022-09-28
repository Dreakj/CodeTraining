package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.entity.frontvo.UcenterMemberPay;
import com.atguigu.eduservice.mapper.EduCommentMapper;
import com.atguigu.eduservice.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-09-20
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {
    @Autowired
    EduCommentService eduCommentService;

    @Override
    @PostMapping("getInfoUc/{id}")
    public UcenterMemberPay getInfo(String id) {
        //根据用户id获取用户信息
//        UcenterMemberPay ucenterMember = eduCommentService.getById(id);
//       UcenterMemberPay memeber = new UcenterMemberPay();
//        BeanUtils.copyProperties(ucenterMember,memeber);
//        return memeber;
        return null;
    }
}
