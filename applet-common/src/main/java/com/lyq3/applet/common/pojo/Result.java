package com.lyq3.applet.common.pojo;

import java.io.Serializable;

/**
 * @author 卡卢比
 * @createTime 2018年07月24日 21:05
 * @description REST接口统一返回封装类
 */

public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**响应Code*/
    private int code;
    /**响应消息*/
    private String msg;
    /**响应数据*/
    private T data;

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result success() {
        Result res = new Result();
        res.setCode(200);
        res.setMsg("SUCCESS");
        return res;
    }
    public static <T> Result success(T t) {
        Result res = new Result();
        res.setCode(200);
        res.setMsg("SUCCESS");
        res.setData(t);
        return res;
    }

    public static Result fail() {
        Result res = new Result();
        res.setCode(-1);
        res.setMsg("FAIL");
        return res;
    }

    public static Result fail(String msg) {
        Result res = new Result();
        res.setCode(-1);
        res.setMsg(msg);
        return res;
    }

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
