package com.atguigu.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-09-28-上午9:23
 */
@Component
@FeignClient(value = "service-order", fallback = OrderFile.class)
public interface OrderClient {
    @GetMapping("/orderservice/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("memberid") String memberid,
                               @PathVariable("id") String id);

}
