package com.atguigu.eduservice.client;

import com.atguigu.eduservice.entity.frontvo.UcenterMemberPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-09-19-下午3:42
 */
@Component
@FeignClient(name = "service-ucenter", fallback = UcenterClientImpl.class)
public interface UcenterClient {
    //根据用户id获取用户信息
    @GetMapping("/educenter/member/getMemberInfo/{memberId}")
    public UcenterMemberPay getUcenterPay(@PathVariable("memberId") String
                                                  memberId);
}