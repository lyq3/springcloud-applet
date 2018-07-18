package com.lyq3.applet.sso.server.test.mapper;

import com.lyq3.applet.sso.server.entity.po.User;
import com.lyq3.applet.sso.server.mapper.UserMapper;
import com.lyq3.applet.sso.server.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends BaseTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testAddOne() {
        User user = new User();
        user.setUsername("卡卢比");
        user.setSex(1);
        userMapper.insert(user);
    }

}
