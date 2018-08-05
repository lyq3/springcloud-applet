package com.lyq3.applet.sso.common.entity.vo;

import com.lyq3.applet.sso.common.entity.po.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 卡卢比
 * @createTime 2018年07月24日 23:13
 * @description 登陆后需要存储的信息
 */
@Data
public class LoginSession implements Serializable {
    private static final long serialVersionUID = 1L;
    /**用户信息*/
    private User user;
    /**登录时的回调地址*/
    private String backUrl;
    /**注册子系统的URL（用于注销登录）*/
    private String registerService;
}
