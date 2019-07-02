package com.chryl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/demo")
public class DemoController {
    @GetMapping("/index")
    public String index() {
        // 默认首页，谁都可以访问
        return "/manage/demo/demo-index";
    }

    @GetMapping("/login")
    public String login() {
        // 登录页面，谁都可以访问
        return "/manage/demo/demo-login";
    }

    @GetMapping("/error")
    @ResponseBody
    public String error() {
        // 登录失败页面，所有用户登录失败时提示错误信息
        return "登录失败，账号密码错误";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        // hello 请求，任务用户都可以访问
        return "hello";
    }

    @GetMapping("/info1")
    @ResponseBody
    public String info1() {
        // 信息页面，只有 ADMIN 角色才能访问
        return "admin info1";
    }

    @GetMapping("/info2")
    @ResponseBody
    public String info2() {
        // 信息页面，只有 USER 角色才能访问
        return "user info2";
    }

}