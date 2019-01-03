package com.jiuxian.config.shiro;

import com.jiuxian.config.ShiroConfig;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.UUID;

public class ShiroSessionDao extends CachingSessionDAO {
    private RedisTemplate<String, Session> redisTemplate;

    public ShiroSessionDao(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    protected Serializable generateSessionId(Session session) {
        return ShiroConfig.prefix + UUID.randomUUID().toString();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        SimpleSession simpleSession = ((SimpleSession) session);
        simpleSession.setId(sessionId);
        redisTemplate.opsForValue().set(sessionId.toString(), simpleSession);
        return sessionId;
    }

    @Override
    protected void doUpdate(Session session) {
        redisTemplate.opsForValue().set(session.getId().toString(), session);
    }

    @Override
    protected void doDelete(Session session) {
        redisTemplate.delete(session.getId().toString());
    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = redisTemplate.opsForValue().get(sessionId.toString());
        return session;
    }
}
