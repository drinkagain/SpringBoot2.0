package com.jiuxian.combination;

import org.springframework.context.annotation.Bean;

/**
 * Author: LIU ZEJUN
 * Date: 2019-01-29 10:13:00
 * Comment:
 */

@ComponentScanConfig("com.jiuxian.combination")
public class CombinationAnnotationConfig {

    @Bean
    public CombinationAnnotationTestService combinationTestService() {
        return new CombinationAnnotationTestService();
    }
}
