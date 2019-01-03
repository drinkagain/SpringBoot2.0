package com.jiuxian.config.shiro;

import com.jiuxian.common.utils.SerializeUtil;
import com.jiuxian.config.ShiroConfig;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ShiroRedisCache<K, V> implements Cache<K, V> {
    private RedisTemplate redisTemplate;

    public ShiroRedisCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @Override
    public V get(K k) throws CacheException {
        if (k == null) {
            return null;
        }
        return (V) redisTemplate.opsForValue().get(getKey(k));
    }

    @Override
    public V put(K k, V v) throws CacheException {
        if (k == null || v == null) {
            return null;
        }
        redisTemplate.opsForValue().set(getKey(k), v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        if (k == null) {
            return null;
        }
        String key = getKey(k);
        V v = (V) redisTemplate.opsForValue().get(key);
        redisTemplate.delete(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    @Override
    public int size() {
        return redisTemplate.getConnectionFactory().getConnection().dbSize().intValue();
    }

    @Override
    public Set<K> keys() {
        String keys = (ShiroConfig.prefix + "*");
        return (Set<K>) redisTemplate.keys(keys);
    }

    @Override
    public Collection<V> values() {
        Set<K> keys = keys();
        ArrayList<V> values = new ArrayList<>(keys.size());
        keys.forEach(k -> values.add(get(k)));
        return values;
    }

    private String getKey(K key) {
        if (key instanceof String) {
            String preKey = key.toString();
            return preKey;
        } else {
            return SerializeUtil.serialize(key).toString();
        }
    }
}
