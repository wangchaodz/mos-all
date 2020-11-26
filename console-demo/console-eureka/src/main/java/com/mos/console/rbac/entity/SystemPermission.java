package com.mos.console.rbac.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @date: 2020/11/17 18:56
 * @author: wangchaodz@gmail.com
 */
@Data
public class SystemPermission implements Serializable {

    private Long id;
    private String permissionsName;

}
