package com.atguigu.educms.controller;

import com.atguigu.commonutils.R;
import com.atguigu.educms.entity.CrmBanner;
import com.atguigu.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-08-30-下午12:08
 */
@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
@Api(description = "网站首页Banner列表")
public class BannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("getAllBanner")
    public R getAllBanner() {
        //根据id降序排列，显示排列之后的前两条记录
//        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
//        wrapper.orderByDesc("id");
//        wrapper.last("limit 2");
        List<CrmBanner> list = bannerService.selectAllBanner();
        return R.ok().data("list", list);
    }
}
