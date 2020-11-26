package com.mos.server.eureka.controller;

import com.mos.server.eureka.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @date: 2020/10/23 15:02
 * @author: wangchaodz@gmail.com
 */
@RefreshScope
@RestController
public class ServiceController {

    @Autowired
    private ServiceConfig serviceConfig;

    @GetMapping("/service_info")
    public ServiceConfig getServiceInfo(){
        return serviceConfig;
    }

}
