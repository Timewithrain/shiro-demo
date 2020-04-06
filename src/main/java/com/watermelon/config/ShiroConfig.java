package com.watermelon.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    //通过Spring对UserRealm对象进行托管
    @Bean(name="userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        /**
         * anon: 无需认证即可访问
         * authc: 认证以后即可访问
         * user: 使用记住我功能后即可访问
         * perms: 拥有特定资源的权限后即可访问
         * role: 拥有特定角色权限后即可访问
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
//        filterMap.put("/user/add","authc");
        //usr路径下的所有页面都进行验证拦截
        filterMap.put("/user/*","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        //设置登录url,当没有权限时默认跳转至登陆页面
        bean.setLoginUrl("/toLogin");

        return bean;
    }

}
