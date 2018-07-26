package com.lyq3.applet.sso.server.config;

import com.lyq3.applet.sso.server.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 卡卢比
 * @createTime 2018年07月24日 23:42
 * @description web配置
 */
@Configuration
public class WebConfig {
    @Autowired
    private LoginFilter loginFilter;

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //添加过滤器
        registration.setFilter(loginFilter);
        //设置过滤路径，/*所有路径
        registration.addUrlPatterns("/*");
        //设置优先级
        registration.setOrder(1);
        return registration;
    }

}
