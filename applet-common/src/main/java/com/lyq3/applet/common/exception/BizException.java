package com.lyq3.applet.common.exception;

/**
 * @author 卡卢比
 * @createTime 2018年07月24日 22:37
 * @description 业务异常基类
 */
public class BizException extends RuntimeException{
    /**异常代码*/
    private int code;
    /**异常提示*/
    private String msg;
    /**异常跳转的地址*/
    private String url;

    public BizException(BizExceptionEnum bizEnum) {
        this.code = bizEnum.getCode();
        this.msg = bizEnum.getMsg();
        this.url = bizEnum.getUrl();
    }

    public BizException(int code, String msg, String url) {
        this.code = code;
        this.msg = msg;
        this.url = url;
    }
    public BizException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public BizException setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BizException setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BizException setUrl(String url) {
        this.url = url;
        return this;
    }
}
