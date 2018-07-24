package com.lyq3.applet.sso.common.util;

import com.lyq3.applet.sso.common.constant.RedisConstant;

/**
 * @author 卡卢比
 * @createTime 2018年07月24日 23:27
 * @description
 */
public class LoginUtil {
    public static String getLoginKey(String uuid){
        return RedisConstant.SSO_SESSIONID + "#" + uuid;
    }
}
