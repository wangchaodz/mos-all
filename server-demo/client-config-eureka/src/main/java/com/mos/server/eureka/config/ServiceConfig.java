package com.mos.server.eureka.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description:
 * @date: 2020/10/23 14:51
 * @author: wangchaodz@gmail.com
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "service")
@Configuration
public class ServiceConfig {

    private String name;

    private String profile;

    private String version;

    //被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次
    @PostConstruct
    public void init() {
        log.info("name: {}" , name);
        log.info("profile: {}" , profile);
        log.info("version: {}" , version);
    }
}
