package com.lyq3.applet.user.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 卡卢比
 * @createTime 2018年07月26日 21:36
 * @description 用户中心启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.lyq3.applet")
@MapperScan("com.lyq3.applet.shopping.cart.mapper**")
@ComponentScan("com.lyq3.applet")
public class UserCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplication.class,args);

        System.out.println("===用户中心中心启动成功===\n");
    }
}
