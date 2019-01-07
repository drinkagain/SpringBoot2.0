package com.jiuxian.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Author: jiuxian
 * Date: 2019-01-07 22:38:00
 * Comment:
 */

@Service
public class AsyncService {

    @Async
    public void executeAsync1() throws InterruptedException {
        Thread.sleep(20);
        System.out.println("异步任务::1");

    }

    @Async
    public void executeAsync2() {
        System.out.println("异步任务::2");
    }

}
