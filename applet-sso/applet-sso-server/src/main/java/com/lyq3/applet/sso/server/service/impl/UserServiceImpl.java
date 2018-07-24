package com.lyq3.applet.sso.server.service.impl;

import com.lyq3.applet.sso.common.constant.RedisConstant;
import com.lyq3.applet.sso.common.entity.po.User;
import com.lyq3.applet.sso.common.entity.vo.LoginSession;
import com.lyq3.applet.sso.common.util.LoginUtil;
import com.lyq3.applet.sso.server.mapper.UserMapper;
import com.lyq3.applet.sso.server.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 卡卢比
 * @createTime 2018年07月24日 21:44
 * @description UserService实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User findByUserName(String username) {
        User user = userMapper.selectOne(new User().setUsername(username));
        return user;
    }

    @Override
    public String doLogin(User user,String backUrl) {
        //存到redis的key
        String uuid = UUID.randomUUID().toString();
        String sessionId = LoginUtil.getLoginKey(uuid);
        //存储的数据
        LoginSession session = new LoginSession();
        session.setUser(user);
        session.setBackUrl(backUrl);
        //30分钟过期
        redisTemplate.opsForValue().set(sessionId,session,30, TimeUnit.MINUTES);
        return uuid;
    }
}
