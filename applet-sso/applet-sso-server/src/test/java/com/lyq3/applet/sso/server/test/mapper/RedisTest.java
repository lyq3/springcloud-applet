package com.lyq3.applet.sso.server.test.mapper;

import com.lyq3.applet.sso.server.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 卡卢比
 * @createTime 2018年07月22日 19:51
 * @description
 */
public class RedisTest extends BaseTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void test(){
        redisTemplate.opsForValue().set("test","sso");
    }
}
