package com.mos.module.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description:
 * @date: 2020/11/4 19:42
 * @author: wangchaodz@gmail.com
 */
@EnableDiscoveryClient
@SpringBootApplication
public class RedisJedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisJedisApplication.class, args);
    }

}