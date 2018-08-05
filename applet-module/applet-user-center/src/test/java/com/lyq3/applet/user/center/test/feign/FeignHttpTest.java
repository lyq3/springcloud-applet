package com.lyq3.applet.user.center.test.feign;

import com.lyq3.applet.common.pojo.Result;
import com.lyq3.applet.sso.client.sso.SsoRestClient;
import com.lyq3.applet.sso.common.entity.vo.LoginSession;
import com.lyq3.applet.user.center.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 卡卢比
 * @createTime 2018年08月05日 16:12
 * @description
 */
public class FeignHttpTest extends BaseTest {
    @Autowired
    private SsoRestClient ssoRestClient;
    @Test
    public void testGetHttp(){
        Result<LoginSession> check = ssoRestClient.check("9310f977-24c1-4211-a651-9b4d402a3f4c");

        System.out.println(check);
    }
}
