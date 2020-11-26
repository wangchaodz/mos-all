package com.mos.module.redis.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 使用缓存
 * 1、先删除关联缓存(主要是考虑更新操作，会修改缓存内容)
 * 2、若命中缓存，更新缓存，并返回值，否则3
 * 3、若未命中，执行目标方法后，存储缓存，并返回值
 * @date: 2020/11/12 9:47
 * @author: wangchaodz@gmail.com
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCache {

    /**
     * @return 是否使用缓存 true:使用，false:不使用
     */
    boolean useCache() default false;

    /**
     * @return 是否存储缓存
     */
    boolean isAdd() default true;

    /**
     * @return 如果更新缓存
     */
    boolean isUpdate() default true;

    /**
     * @return 是否删除关联缓存
     */
    boolean isDelRelation() default false;

    /**
     * @return 失效时间，若-1，永久有效
     */
    long expireTime() default -1;

    /**
     * @return 如果更新, 更新后的失效时间，若-1，永久有效
     */
    long expireUpdateTime() default -1;

    /**
     * @return 关联缓存
     */
    String relationKey() default "";

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
