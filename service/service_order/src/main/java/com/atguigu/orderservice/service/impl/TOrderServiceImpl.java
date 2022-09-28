package com.atguigu.orderservice.service.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.orderservice.client.EduClient;
import com.atguigu.orderservice.client.UcenterClient;
import com.atguigu.orderservice.entity.TOrder;
import com.atguigu.orderservice.mapper.TOrderMapper;
import com.atguigu.orderservice.service.TOrderService;
import com.atguigu.orderservice.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-09-19
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    public EduClient eduClient;
    @Autowired
    public UcenterClient ucenterClient;

    @Override
    public String saveOrder(String courseId, String memberIdByJwtToken) {
        //通过远程调用获取用户信息
        CourseWebVoOrder courseInfo = eduClient.getCourseInfoOrder(courseId);
        //通过远程调用获取课程信息
        UcenterMemberOrder userInfo = ucenterClient.getUserInfoOrder(memberIdByJwtToken);
        TOrder tOrder = new TOrder();
        tOrder.setOrderNo(OrderNoUtil.getOrderNo());

        tOrder.setCourseId(courseId);
        tOrder.setCourseTitle(courseInfo.getTitle());
        tOrder.setCourseCover(courseInfo.getCover());
        tOrder.setTeacherName(courseInfo.getTeacherName());
        tOrder.setTotalFee(courseInfo.getPrice());
        tOrder.setMemberId(memberIdByJwtToken);
        tOrder.setMobile(userInfo.getMobile());
        tOrder.setNickname(userInfo.getNickname());
        tOrder.setStatus(0);
        tOrder.setPayType(1);
        baseMapper.insert(tOrder);
        return tOrder.getOrderNo();
    }
}
