package com.mos.console.rbac.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description:
 * @date: 2020/11/17 18:54
 * @author: wangchaodz@gmail.com
 */
@Data
@Builder
public class SystemRole implements Serializable {

    private Long id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<SystemPermission> permissions;

}
