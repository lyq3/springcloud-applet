package com.lyq3.applet.sso.client.sso;

import com.lyq3.applet.common.pojo.Result;
import com.lyq3.applet.sso.common.entity.vo.LoginSession;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 卡卢比
 * @createTime 2018年07月26日 20:36
 * @description 请求SSO认证中心的Feign接口
 */
@FeignClient("applet-sso-server")
public interface SsoRestClient {
    /**
     *向认证中心发起Token验证请求
     * @param token
     * @return
     */
    @GetMapping("/sso/check")
    Result<LoginSession> check(@RequestParam("token") String token);

    /**
     * 向认证中心发起注销请求
     * @return
     */
    @GetMapping("/sso/logout")
    Result logout();

}
