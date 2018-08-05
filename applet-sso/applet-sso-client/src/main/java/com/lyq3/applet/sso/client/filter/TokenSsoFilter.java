package com.lyq3.applet.sso.client.filter;

import com.lyq3.applet.common.pojo.Result;
import com.lyq3.applet.sso.client.sso.SsoRestClient;
import com.lyq3.applet.sso.common.constant.PathConstant;
import com.lyq3.applet.sso.common.constant.SysConstant;
import com.lyq3.applet.sso.common.entity.vo.LoginSession;
import com.lyq3.applet.sso.common.util.LoginUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 卡卢比
 * @createTime 2018年07月26日 20:18
 * @description 单点登录拦截Token的Filter
 */
@Component
public class TokenSsoFilter implements Filter {
    @Autowired
    private SsoRestClient ssoClient;
    @Value("${sso.server.url}")
    private String SsoServerUrl;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse)servletResponse;
        HttpSession session = req.getSession();
        String url = req.getRequestURL().toString();
        //获取Token
        String token =  getToken(req);
        //获取存放Token的Cookie
        Cookie tokenCookie = getTokenCookie(req);

        //没有Token则未登录，跳转到登录页面
        if (StringUtils.isEmpty(token)) {
            res.sendRedirect(SsoServerUrl + PathConstant.LOGIN_URL+"?"+SysConstant.BACKURL+"=" + url);
            return;
        }
        //如果当前子系统已登录就放行
        if (session.getAttribute(LoginUtil.getLoginKey(token)) != null){
            filterChain.doFilter(servletRequest, servletResponse);
            return ;
        }
        //如果子系统未登录则去认证中心验证是否登录
        //验证Token是否有效
        Result<LoginSession> result = ssoClient.check(token);
        //无效跳转登录页面
        if (result == null || result.getData() == null) {
            res.sendRedirect(SsoServerUrl + PathConstant.LOGIN_URL+ "?"+SysConstant.BACKURL+"=" + url);
            return;
        }
        //有效则登录当前子系统
        LoginSession loginSession = result.getData();
        session.setAttribute(LoginUtil.getLoginKey(token),loginSession);
        //过期时间，10分钟（子系统过期时间一定比认证中心短）
        session.setMaxInactiveInterval(10);
        //cookie续期
        if (tokenCookie == null) {
            tokenCookie = new Cookie(SysConstant.TOKEN_NAME,token);

        } else {
            tokenCookie.setValue(token);
        }
        tokenCookie.setPath("/");
        tokenCookie.setHttpOnly(true);
        tokenCookie.setMaxAge(30*60);
        res.addCookie(tokenCookie);

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }


    /**
     * 获取Token
     * @param req
     * @return
     */
    private String getToken(HttpServletRequest req) {
        //参数中获取Token
        String token = req.getParameter(SysConstant.TOKEN_NAME);
        if (StringUtils.isNotEmpty(token)){
            return token;
        }

        //请求头中获取Token
        token = req.getHeader(SysConstant.TOKEN_NAME);
        if (StringUtils.isNotEmpty(token)){
            return token;
        }

        //cookie中获取Token
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (SysConstant.TOKEN_NAME.equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }

    /**
     * 获取存放Token的Cookie
     * @param req
     * @return
     */
    private Cookie getTokenCookie(HttpServletRequest req){
        Cookie tokenCookie = null;
        //cookie中获取Token
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (SysConstant.TOKEN_NAME.equals(cookie.getName())) {
                    tokenCookie = cookie;
                }
            }
        }
        return tokenCookie;
    }

}
