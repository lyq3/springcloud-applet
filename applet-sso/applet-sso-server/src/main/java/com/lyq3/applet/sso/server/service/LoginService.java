package com.lyq3.applet.sso.server.service;

import com.lyq3.applet.sso.common.entity.po.User;
import com.lyq3.applet.sso.common.entity.vo.LoginSession;

/**
 * 登录Service
 * @author 卡卢比
 */
public interface LoginService {
    /**
     * 登录存Redis
     * @param user
     */
    String doLogin(User user, String backUrl);

    /**
     * 检查Token有效性
     * @param token
     * @return
     */
    LoginSession checkToken(String token);
}
