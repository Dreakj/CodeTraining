package com.atguigu.staservice.service;

import com.atguigu.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-09-28
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    public void createStatisticsByDay(String day);

}
