package com.lyq3.applet.shopping.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 卡卢比
 * @createTime 2018年07月26日 21:44
 * @description 购物车服务启动类
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lyq3.applet.shopping.cart.mapper**")
@ComponentScan("com.lyq3.applet")
public class ShopCartApplication {
    public static void main(String[] args) {
        System.out.println("===用户中心中心启动成功===\n");
    }
}
