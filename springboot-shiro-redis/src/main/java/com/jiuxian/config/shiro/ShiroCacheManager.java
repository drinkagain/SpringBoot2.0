package com.jiuxian.config.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

public class ShiroCacheManager extends AbstractCacheManager {
    private RedisTemplate redisTemplate;

    public ShiroCacheManager(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws IllegalArgumentException, CacheException {
        return new ShiroRedisCache<>(redisTemplate);
    }

    @Override
    protected Cache createCache(String name) throws CacheException {
        return new ShiroRedisCache<>(redisTemplate);
    }
}
