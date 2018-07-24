package com.lyq3.applet.sso.server.filter;

import com.lyq3.applet.sso.common.constant.PathConstant;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 卡卢比
 * @createTime 2018年07月23日 20:15
 * @description 单点登录Server端拦截Filter
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        String uri = req.getRequestURI();

        //注销请求、登陆请求、登陆页面放行
        if (PathConstant.LOGOUT_URL.equals(uri) || PathConstant.LOGIN_URL.equals(uri) || PathConstant.DO_LOGIN_URL.equals(uri)) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //已登录的返回Token
        //TODO

        //其他请求跳转到登录页面
        res.sendRedirect(PathConstant.LOGIN_URL);



    }

    @Override
    public void destroy() {

    }
}
