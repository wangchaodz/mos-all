package com.mos.module.redis.client;

/**
 * @Description:
 * @date: 2020/11/24 17:15
 * @author: wangchaodz@gmail.com
 */
public interface RedisClient {

    /**
     * DEL key [key ...]
     * 删除给定的一个或多个 key 。 不存在的 key 会被忽略。
     * 时间复杂度： O(N)， N 为被删除的 key 的元素数量。
     *
     * @param keys 给定的一个或多个 key
     * @return
     */
    int del(String... keys);

    /**
     * 序列化给定 key，并返回被序列化的值，使用 RESTORE 命令可以将这个值反序列化为 Redis 键。
     *
     * @param key
     * @return 如果 key 不存在，那么返回 nil 。 否则，返回序列化之后的值。
     */
    String dump(String key);



}
