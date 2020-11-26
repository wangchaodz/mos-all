package com.mos.console.rbac.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description:
 * @date: 2020/11/17 18:54
 * @author: wangchaodz@gmail.com
 */
@Data
public class SystemUser implements Serializable {

    private Long id;
    private String userName;
    private String password;

    /**
     * 用户对应的角色集合
     */
    private Set<SystemRole> roles;

}
