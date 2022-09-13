package com.atguigu.msmservice.service;

import java.util.Map;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-09-02-上午11:34
 */

public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
