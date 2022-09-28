package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-09-28-下午4:58
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping("/countregister/{day}")
    public R registerCount(@PathVariable("day") String day);

}
