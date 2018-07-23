package com.lyq3.applet.sso.common.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lyq3.applet.sso.common.entity.po.User;
import com.lyq3.applet.sso.common.util.JwtUtils;

/**
 * @author 卡卢比
 * @createTime 2018年07月23日 21:27
 * @description
 */
public class JwtUtilsTest {

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("卡卢比");
        user.setSex(1);
        String jwtStr = JwtUtils.buildJWT(JSON.toJSONString(user,SerializerFeature.WRITE_MAP_NULL_FEATURES));
        System.out.println(jwtStr);
        String decrypt = JwtUtils.decrypt(jwtStr);
        System.out.println(decrypt);
    }

}
