package com.mos.module.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description:
 * @date: 2020/11/4 19:42
 * @author: wangchaodz@gmail.com
 */
@EnableAspectJAutoProxy(proxyTargetClass=true, exposeProxy=true)
@EnableDiscoveryClient
@SpringBootApplication
public class RedisLuttuceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisLuttuceApplication.class, args);
    }

}