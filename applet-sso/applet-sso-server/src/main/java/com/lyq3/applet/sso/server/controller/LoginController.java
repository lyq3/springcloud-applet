package com.lyq3.applet.sso.server.controller;

import com.lyq3.applet.common.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 卡卢比
 * @createTime 2018年07月24日 21:01
 * @description 登录控制器
 */
@Controller
@RequestMapping("/sso")
public class LoginController {
    /**
     * 登录页面
     * @return
     */
    @GetMapping("/page/login")
    public String login () {
        return "";
    }

    /**
     * 登录请求
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result doLogin(){
        return Result.success();
    }

    /**
     * 注销请求
     * @return
     */
    public Result logout(){
        return Result.success();
    }
}
