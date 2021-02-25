package com.mos.console.rbac.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description:
 * @date: 2020/11/17 18:54
 * @author: wangchaodz@gmail.com
 */
@Data
@Builder
public class SystemUser implements Serializable {

    private Long id;
    private String loginName;
    private String loginPwd;

    /**
     * 用户对应的角色集合
     */
    private Set<SystemRole> roles;

}
