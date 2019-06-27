package com.jiuxian.springbootlock.lock;

import com.jiuxian.springbootlock.common.LockService;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-27 11:31:23
 * *
 * @description: 使用redis锁
 **/
public class RedisLockServiceImpl implements LockService<Long> {

    private final RedisTemplate<String, Long> redisTemplate;

    public RedisLockServiceImpl(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean setIfAbsent(String key, Long value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public void expire(String key, int seconds) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    @Override
    public Long get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Long getAndSet(String key, Long value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public void release(String key) {
        redisTemplate.delete(key);
    }
}
