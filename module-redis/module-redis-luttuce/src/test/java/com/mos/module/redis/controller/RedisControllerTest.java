package com.mos.module.redis.controller;

import com.mos.common.redis.CommonRedisLuttuceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

import static org.junit.Assert.*;

/**
 * @Description:
 * @date: 2020/11/5 14:31
 * @author: wangchaodz@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonRedisLuttuceApplication.class)
public class RedisControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate<String, Serializable> lettuceRedisCacheTemplate;

    @Test
    public void getKey() {
        
    }

    @Test
    public void setKey() {
    }
}