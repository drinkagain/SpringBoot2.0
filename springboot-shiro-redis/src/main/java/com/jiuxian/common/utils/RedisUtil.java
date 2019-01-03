package com.jiuxian.common.utils;

import redis.clients.jedis.Jedis;

public class RedisUtil {
    private static Jedis jedis = null;

    static {
        jedis = new Jedis("localhost");
    }

    public void test(String key, Object value) {
    }
}
