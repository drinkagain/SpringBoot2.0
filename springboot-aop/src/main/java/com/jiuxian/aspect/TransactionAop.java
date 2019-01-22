package com.jiuxian.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-22 11:47:00
 * Comment:
 */
@Aspect
@Component
public class TransactionAop {

    @Pointcut("execution(* com.jiuxian..service.*Service.save*(String))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void beginTransaction() {
        System.out.println("before beginTransaction");
    }

    @After("pointcut()")
    public void commit() {
        System.out.println("after commit");
    }

    @AfterReturning(value = "pointcut()", returning = "obj")
    public void afterReturning(Object obj) {
        System.out.println("afterReturning :: " + obj);
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("afterThrowing afterThrowing  rollback");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("around");
            return joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("around");
        }
    }
}


