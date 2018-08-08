package com.lyq3.applet.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 卡卢比
 * @createTime 2018年08月08日 21:00
 * @description 服务网关启动类
 */
@SpringCloudApplication
@EnableZuulProxy
public class GetWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetWayApplication.class,args);
    }
}
