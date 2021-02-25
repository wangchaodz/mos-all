package com.mos.console.rbac.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @date: 2020/11/17 19:29
 * @author: wangchaodz@gmail.com
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //shiro内置过滤器
        /**
         * anon 无需认证
         * authc 必须认证
         * user 如果使用remerberme可以直接访问
         * perms必须得到该资源的权限
         * role必须得到角色的权限
         */
        Map<String, String> filterChain = new LinkedHashMap<>();
        filterChain.put("/thymeleaf", "anon");
        filterChain.put("/login", "anon");
        //授权拦截
//        filterChain.put("/shiro/add", "perms[user:add]");
//        filterChain.put("/shiro/update", "authc");

        // filterChain.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChain);
        //登录页面
        shiroFilterFactoryBean.setLoginUrl("/loginHtml");
        //未授权提示
        shiroFilterFactoryBean.setUnauthorizedUrl("/unrole");
        return shiroFilterFactoryBean;
    }

    //安全管理器
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("customRealm") CustomRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    //realm类
    @Bean("customRealm")
    public CustomRealm getRealm() {
        return new CustomRealm();
    }

    //配置shirodialect 用于thymeleaf和shiro配合使用
    @Bean("shiroDialect")
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();

    }
}
