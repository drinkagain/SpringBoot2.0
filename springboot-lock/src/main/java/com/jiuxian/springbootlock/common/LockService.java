package com.jiuxian.springbootlock.common;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-27 11:24:58
 * *
 * @description: lock service
 **/
public interface LockService<T> {

    /**
     * 如果存在key 返回false,不存在设置value，返回true  原子操作
     * @param key   key
     * @param value value
     * @return boolean
     */
    boolean setIfAbsent(String key, T value);

    /**
     * 设置 key 的最大存活时间
     * @param key     key
     * @param seconds seconds
     */
    void expire(String key, int seconds);

    /**
     * get
     * @param key key
     * @return value
     */
    T get(String key);

    /**
     * 获取old value，设置新的value  原子操作
     * @param key   key
     * @param value newValue
     * @return oldValue
     */
    T getAndSet(String key, T value);

    /**
     * 释放key
     * @param key key
     */
    void release(String key);
}
