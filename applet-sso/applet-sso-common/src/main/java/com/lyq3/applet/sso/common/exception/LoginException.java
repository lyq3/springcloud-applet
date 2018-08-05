package com.lyq3.applet.sso.common.exception;

import com.lyq3.applet.common.exception.BizException;
import com.lyq3.applet.common.exception.BizExceptionEnum;

/**
 * @author 卡卢比
 * @createTime 2018年08月05日 14:10
 * @description 登录异常
 */
public class LoginException extends BizException {
    public LoginException(BizExceptionEnum bizEnum) {
        super(bizEnum);
    }

    public LoginException(int code, String msg, String url) {
        super(code, msg, url);
    }

    public LoginException(int code, String msg) {
        super(code, msg);
    }
}
