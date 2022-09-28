package com.atguigu.orderservice.client;

import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-09-21-下午4:43
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据用户id查询用户信息
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
