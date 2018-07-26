package com.lyq3.applet.sso.server.service;

import com.lyq3.applet.sso.common.entity.po.User;
import com.lyq3.applet.sso.common.entity.vo.LoginSession;

/**
 * 用户Service
 * @author 卡卢比
 */
public interface UserService {
    /**
     * 根据Username查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 登录存Redis
     * @param user
     */
    String doLogin(User user,String backUrl);

    /**
     * 检查Token有效性
     * @param token
     * @return
     */
    LoginSession checkToken(String token);
}
