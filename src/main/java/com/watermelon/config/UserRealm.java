package com.watermelon.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class UserRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
        System.out.println("授权Authorization");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证Authentication");

        String name = "root";
        String password = "123";
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //当用户名不匹配时返回null,shiro自动抛出UnknownAccountException异常
        if (!userToken.getUsername().equals(name)){
            return null;
        }
        return new SimpleAuthenticationInfo("",password,"");
    }
}
