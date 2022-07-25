package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @作者：刘子越（Derrick）
 * @创造日期：2022-07-15-下午12:46
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    //login
    @PostMapping("login")
    public R login() {

        return R.ok().data("token", "admin");
    }

    //info
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name","admin").data("avatar", "Users/liuziyue/Downloads/archive/icons3561856_dead_blank_emoticon.png");
    }

}
