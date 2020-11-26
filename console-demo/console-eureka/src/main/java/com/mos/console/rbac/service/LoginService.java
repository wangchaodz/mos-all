package com.mos.console.rbac.service;

import com.mos.console.rbac.entity.SystemUser;

/**
 * @Description:
 * @date: 2020/11/17 19:04
 * @author: wangchaodz@gmail.com
 */
public interface LoginService {

    SystemUser getByUserName(String userName);
}
