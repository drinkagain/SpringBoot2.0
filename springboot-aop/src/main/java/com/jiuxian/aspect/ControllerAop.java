package com.jiuxian.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: zejun.liu
 * *
 * @date: 2019/11/11 10:09
 * *
 * @description:
 */
@Aspect
@Component
public class ControllerAop {
    /**
     * 定义拦截规则：
     * 有@RequestMapping注解的方法。
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut() {
    }



    @Around("controllerMethodPointcut()")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("interceptor .....");
        return pjp.proceed();
    }

}
