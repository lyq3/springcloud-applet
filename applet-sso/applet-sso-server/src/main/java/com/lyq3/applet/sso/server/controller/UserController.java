package com.lyq3.applet.sso.server.controller;

import com.lyq3.applet.sso.common.entity.po.User;
import com.lyq3.applet.sso.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/add")
    public User add(){
        User user = new User();
        user.setUsername("卡卢比");
        user.setSex(1);
        userMapper.insert(user);

        return user;
    }
}
