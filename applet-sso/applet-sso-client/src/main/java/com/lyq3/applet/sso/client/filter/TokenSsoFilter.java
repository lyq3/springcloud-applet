package com.lyq3.applet.sso.client.filter;

import com.lyq3.applet.common.pojo.Result;
import com.lyq3.applet.sso.client.sso.SsoRestClient;
import com.lyq3.applet.sso.common.constant.PathConstant;
import com.lyq3.applet.sso.common.constant.SysConstant;
import com.lyq3.applet.sso.common.entity.vo.LoginSession;
import com.lyq3.applet.sso.common.util.LoginUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
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
        String token = req.getParameter(SysConstant.TOKEN_NAME);
        if (StringUtils.isEmpty(token)){
            token = req.getHeader(SysConstant.TOKEN_NAME);
        }

        if (StringUtils.isEmpty(token)) {
            res.sendRedirect(SsoServerUrl + PathConstant.LOGIN_URL+"?"+SysConstant.BACKURL+"=" + url);
            return;
        }
        //验证Token是否有效
        Result<LoginSession> result = ssoClient.check(token);
        //无效跳转登录页面
        if (result == null || result.getData() == null) {
            res.sendRedirect(SsoServerUrl + PathConstant.LOGIN_URL+ "?"+SysConstant.BACKURL+"=" + url);
            return;
        }
        //有效则登录
        LoginSession loginSession = result.getData();
        session.setAttribute(LoginUtil.getLoginKey(token),loginSession);

        doFilter(servletRequest, servletResponse,filterChain);


    }

    @Override
    public void destroy() {

    }
}
