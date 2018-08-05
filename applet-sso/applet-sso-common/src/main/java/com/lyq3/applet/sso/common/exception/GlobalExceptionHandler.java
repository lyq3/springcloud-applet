package com.lyq3.applet.sso.common.exception;

import com.lyq3.applet.common.exception.BizException;
import com.lyq3.applet.common.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局的权限验证和异常拦截
 * @author Lyq
 *
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Result bussinessException(BizException e) {
        // 输出JSON
        Result res = new Result();
        res.setCode(e.getCode());
        res.setMsg(e.getMsg());
        log.error(e.getMsg());
        e.printStackTrace();
        return res;
    }
    /**
     * 其他未知异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result bussinessException(Exception e) {
        e.printStackTrace();
        return Result.fail("服务器异常");
    }
}
