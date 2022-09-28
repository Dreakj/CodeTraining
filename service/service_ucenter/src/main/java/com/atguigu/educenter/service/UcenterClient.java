package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.service.impl.UcenterMemberServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-09-19-下午3:24
 */
@Component
@FeignClient(name = "service-ucenter",fallback = UcenterMemberServiceImpl.class)
public interface UcenterClient {
    //根据用户id获取用户信息
    @GetMapping("/ucenterservice/member/getUcenterPay/{memberId}")
    public UcenterMember getUcenterPay(@PathVariable("memberId") String
                                                  memberId);

}
