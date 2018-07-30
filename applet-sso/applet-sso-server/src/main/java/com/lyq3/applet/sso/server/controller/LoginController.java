package com.lyq3.applet.sso.server.controller;

import com.lyq3.applet.common.exception.BizException;
import com.lyq3.applet.common.exception.BizExceptionEnum;
import com.lyq3.applet.common.pojo.Result;
import com.lyq3.applet.sso.common.constant.SysConstant;
import com.lyq3.applet.sso.common.entity.po.User;
import com.lyq3.applet.sso.common.entity.vo.LoginSession;
import com.lyq3.applet.sso.server.service.LoginService;
import com.lyq3.applet.sso.server.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 卡卢比
 * @createTime 2018年07月24日 21:01
 * @description 登录控制器
 */
@Controller
@RequestMapping("/sso")
public class LoginController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    /**
     * 登录页面
     * @return
     */
    @GetMapping("/page/login")
    public String login (String backUrl) {
        return "";
    }

    /**
     * 登录请求
     * @return
     */
    @PostMapping("/login")
    public String doLogin(){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String backUrl = request.getParameter(SysConstant.BACKURL);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BizException(BizExceptionEnum.LOGIN_USERNAME_ISNULL);
        }

        User user = userService.findByUserName(username);
        if (user == null) {
            throw new BizException(BizExceptionEnum.LOGIN_USER_IS_EXIST);
        }
        //密文
        String secret = DigestUtils.md5Hex(password + user.getSalt());
        if (!secret.equals(user.getPassword())) {
            throw new BizException(BizExceptionEnum.LOGIN_PASSWORD_ERROR);
        }

        if (user.getLocked() == -1) {
            throw new BizException(BizExceptionEnum.LOGIN_USER_IS_BLOCK);
        }

        //登录->存Redis
        String sessionId = loginService.doLogin(user,backUrl);

        if (StringUtils.isNotEmpty(backUrl)) {
           return "redirect:" + backUrl + "?"+SysConstant.TOKEN_NAME +"=" + sessionId;
        }
       return "redirect:/";
    }

    /**
     * 注销请求
     * @return
     */
    @GetMapping("/logout")
    @ResponseBody
    public Result logout(){
        return Result.success();
    }

    /**
     * 检查Token是否有效
     * @return
     */
    @GetMapping("/check")
    @ResponseBody
    public Result checkToken(String token) {
        LoginSession isValid = loginService.checkToken(token);
        return Result.success(isValid);
    }
}
