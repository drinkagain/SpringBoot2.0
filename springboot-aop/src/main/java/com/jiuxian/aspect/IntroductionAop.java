package com.jiuxian.aspect;

import com.jiuxian.service.DoSthService;
import com.jiuxian.service.impl.DoSthServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-23 10:57:00
 * Comment:
 */

@Aspect
@Component
public class IntroductionAop {

    @DeclareParents(value = "com.jiuxian..service..*", defaultImpl = DoSthServiceImpl.class)
    public DoSthService doSthService;

}
