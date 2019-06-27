package com.jiuxian.springbootlock.common.aop;

import com.jiuxian.springbootlock.common.LockUtil;
import com.jiuxian.springbootlock.common.annotation.DistributedLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-27 12:27:03
 * *
 * @description: 分布式锁 切面
 **/
@Aspect
@Component
public class DistributedLockAspect {

    @Pointcut(value = "@annotation(lock)", argNames = "lock")
    public void pointcut(DistributedLock lock) {
    }

    @Around(value = "pointcut(lock)", argNames = "joinPoint,lock")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock lock) throws Throwable {
        String lockKey = getLockKey(joinPoint, lock);
        try {
            LockUtil.lock(lockKey);
            return joinPoint.proceed();
        } finally {
            LockUtil.releaseLock(lockKey);
        }
    }

    private String getLockKey(ProceedingJoinPoint joinPoint, DistributedLock lock) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        verify(lock);
        if (lock.parameterKeyIndex() >= 0) {

            return parameterIndexKey(joinPoint, lock.parameterKeyIndex());
        } else if (!StringUtils.isEmpty(lock.parameterKey())) {

            return parameterKey(joinPoint, lock.parameterKey());
        } else {

            return methodKey(joinPoint, lock.methodKey());
        }

    }

    private String parameterIndexKey(ProceedingJoinPoint joinPoint, int parameterKeyIndex) {
        if (joinPoint.getArgs().length - 1 < parameterKeyIndex) {
            throw new IllegalArgumentException("parameterKeyIndex " + parameterKeyIndex + " not found");
        }
        return joinPoint.getArgs()[parameterKeyIndex].toString();
    }

    private String methodKey(ProceedingJoinPoint joinPoint, String lockMethod) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = joinPoint.getTarget().getClass().getMethod(lockMethod, getParameterTypes(joinPoint.getArgs()));
        return (String) method.invoke(joinPoint.getTarget(), joinPoint.getArgs());
    }

    private Class<?>[] getParameterTypes(Object[] args) {
        return Stream.of(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    private String parameterKey(ProceedingJoinPoint joinPoint, String parameterKey) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(parameterKey)) {
                return joinPoint.getArgs()[i].toString();
            }
        }
        throw new IllegalArgumentException("parameterKey " + parameterKey + " not found");
    }


    private void verify(DistributedLock lock) {
        if (lock.parameterKeyIndex() == -1
                && StringUtils.isEmpty(lock.parameterKey())
                && StringUtils.isEmpty(lock.methodKey())) {
            throw new IllegalArgumentException("DistributedLock key not found");
        }
    }
}
