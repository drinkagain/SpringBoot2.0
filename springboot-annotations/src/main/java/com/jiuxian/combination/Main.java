package com.jiuxian.combination;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-29 10:15:00
 * Comment:
 */


public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CombinationAnnotationConfig.class);
        CombinationAnnotationTestService service = context.getBean(CombinationAnnotationTestService.class);
        service.doSth();
        context.close();
    }
}
