package com.mos.console.rbac.config;

import com.mos.console.rbac.entity.SystemPermission;
import com.mos.console.rbac.entity.SystemRole;
import com.mos.console.rbac.entity.SystemUser;
import com.mos.console.rbac.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 自定义Realm用于查询用户的角色和权限信息并保存到权限管理器
 * @date: 2020/11/17 19:05
 * @author: wangchaodz@gmail.com
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * 权限配置类
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object userNameObj = principalCollection.getPrimaryPrincipal();
        if (userNameObj == null || "".equals(userNameObj)) {
            return null;
        }
        String userName = (String) userNameObj;
        SystemUser user = loginService.getByUserName(userName);
        if (user == null) {
            return null;
        }
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        for (SystemRole role : user.getRoles()){
            auth.addRole(role.getRoleName());

            for (SystemPermission permission : role.getPermissions()){
                auth.addStringPermission(permission.getPermissionsName());
            }
        }
        return auth;
    }

    /**
     * 认证配置类
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        Object userNameObj = authenticationToken.getPrincipal();
        if (userNameObj == null || "".equals(userNameObj)) {
            return null;
        }
        String userName = (String) userNameObj;
        SystemUser user = loginService.getByUserName(userName);
        if (user == null) {
            return null;
        }
        //这里验证authenticationToken和simpleAuthenticationInfo的信息
        SimpleAuthenticationInfo auth = new SimpleAuthenticationInfo(userName, user.getLoginPwd(), getName());
        return auth;
    }
}
