package com.mos.console.rbac.service.impl;

import com.mos.console.rbac.entity.SystemRole;
import com.mos.console.rbac.entity.SystemUser;
import com.mos.console.rbac.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {


    @Override
    public SystemUser getByUserName(String userName) {
        SystemRole role = SystemRole.builder().id(1L).roleName("系统管理员").build();

        Set<SystemRole> roles = new HashSet<>();
        roles.add(role);

        return SystemUser.builder().id(1L).loginName(userName).loginPwd("111111").roles(roles).build();
    }
}
