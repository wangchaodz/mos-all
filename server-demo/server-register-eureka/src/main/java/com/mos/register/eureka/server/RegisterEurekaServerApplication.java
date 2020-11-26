package com.mos.register.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableEurekaServer
public class RegisterEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterEurekaServerApplication.class, args);
    }

}
