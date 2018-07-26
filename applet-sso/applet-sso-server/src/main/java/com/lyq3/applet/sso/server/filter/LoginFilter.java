package com.lyq3.applet.sso.server.filter;

import com.lyq3.applet.sso.common.constant.PathConstant;
import com.lyq3.applet.sso.common.constant.SysConstant;
import com.lyq3.applet.sso.common.util.LoginUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 卡卢比
 * @createTime 2018年07月23日 20:15
 * @description 单点登录Server端拦截Filter
 */
@Component
public class LoginFilter implements Filter {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        String uri = req.getRequestURI();

        String token = req.getParameter(SysConstant.TOKEN_NAME);
        if (StringUtils.isEmpty(token)){
            token = req.getHeader(SysConstant.TOKEN_NAME);
        }

        //注销请求、登陆请求、登陆页面放行
        if (PathConstant.LOGOUT_URL.equals(uri) || PathConstant.LOGIN_URL.equals(uri) || PathConstant.DO_LOGIN_URL.equals(uri)) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //已登录放行
        if (StringUtils.isNotEmpty(token)) {
            Object user = redisTemplate.opsForValue().get(LoginUtil.getLoginKey(token));
            if (user != null ) {
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }

        //其他请求跳转到登录页面
        res.sendRedirect(PathConstant.LOGIN_URL);

    }

    @Override
    public void destroy() {

    }
}
