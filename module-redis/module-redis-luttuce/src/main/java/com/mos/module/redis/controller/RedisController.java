package com.mos.module.redis.controller;

import com.mos.common.redis.annotation.UseCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description:
 * @date: 2020/11/4 22:21
 * @author: wangchaodz@gmail.com
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate<String, Serializable> lettuceRedisCacheTemplate;

    @GetMapping("/get/{k}")
    @UseCache(useCache = true,expireTime = 60000L,expireUpdateTime = 60000L)
    public String getKey(@PathVariable("k") String key) {
        long begin = System.currentTimeMillis();
        String value = Objects.requireNonNull(lettuceRedisCacheTemplate.opsForValue().get(key)).toString();
        LOGGER.info("/get/{}===={},cost {} ms",key,value,System.currentTimeMillis() - begin);
        return value;
    }

    @GetMapping("/set/{k}/{v}")
    @UseCache(useCache = true,isAdd = false,isUpdate = false,isDelRelation = true,relationKey = "getKey*")
    public String setKey(@PathVariable("k") String key,@PathVariable("v") String value) throws InterruptedException {
        long begin = System.currentTimeMillis();
        lettuceRedisCacheTemplate.opsForValue().set(key,value);

        Thread.sleep(2000);
        LOGGER.info("/set/{}===={},cost {} ms",key,value,System.currentTimeMillis() - begin);
        return "succ";
    }
}
