package com.jiuxian.job;

import org.springframework.stereotype.Component;

@Component(value = "testJob")
public class TestJob {
    public void testJob() {
        System.out.println(">>>>>>>>JOB RUN OK>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
