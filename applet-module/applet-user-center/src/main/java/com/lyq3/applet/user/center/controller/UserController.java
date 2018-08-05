package com.lyq3.applet.user.center.controller;

import com.lyq3.applet.common.pojo.Result;
import com.lyq3.applet.sso.common.entity.po.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 卡卢比
 * @createTime 2018年08月05日 16:26
 * @description 用户Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{userId}")
    public Result<User> getUserById(@PathVariable Integer userId){
        User user = new User();
        user.setUsername("测试");
        return Result.success(user);
    }
}
