package com.lyq3.applet.common.exception;

/**
 * @author 卡卢比
 * @createTime 2018年07月24日 22:43
 * @description 业务异常枚举
 */
public enum  BizExceptionEnum {
    /**登录异常*/
    LOGIN_PASSWORD_ERROR(1001,"账号或密码错误！",null),
    LOGIN_USERNAME_ISNULL(1002,"账号密码不能为空！",null),
    LOGIN_USER_IS_NOT_EXIST(1003,"该账号未注册!",null),
    LOGIN_USER_IS_BLOCKED(1004,"账号被锁定！",null)
    ;

    private int code;
    private String msg;
    private String url;

    BizExceptionEnum(int code, String msg, String url) {
        this.code = code;
        this.msg = msg;
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public BizExceptionEnum setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BizExceptionEnum setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BizExceptionEnum setUrl(String url) {
        this.url = url;
        return this;
    }
}
