package com.lyq3.applet.sso.server.service.impl;

import com.lyq3.applet.sso.common.entity.po.User;
import com.lyq3.applet.sso.common.entity.vo.LoginSession;
import com.lyq3.applet.sso.common.util.LoginUtil;
import com.lyq3.applet.sso.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 卡卢比
 * @createTime 2018年07月30日 21:14
 * @description
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private RedisTemplate redisTemplate;

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

    @Override
    public LoginSession checkToken(String token) {
        String key = LoginUtil.getLoginKey(token);
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj != null) {
            return (LoginSession)obj;
        }

        return null;
    }
}
