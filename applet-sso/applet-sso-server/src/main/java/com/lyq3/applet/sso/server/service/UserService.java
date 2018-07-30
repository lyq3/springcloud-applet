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

}
