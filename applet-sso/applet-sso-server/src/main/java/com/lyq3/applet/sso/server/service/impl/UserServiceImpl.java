package com.lyq3.applet.sso.server.service.impl;

import com.lyq3.applet.sso.common.entity.po.User;
import com.lyq3.applet.sso.server.mapper.UserMapper;
import com.lyq3.applet.sso.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 卡卢比
 * @createTime 2018年07月24日 21:44
 * @description UserService实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User user = userMapper.selectOne(new User().setUsername(username));
        return user;
    }

}
