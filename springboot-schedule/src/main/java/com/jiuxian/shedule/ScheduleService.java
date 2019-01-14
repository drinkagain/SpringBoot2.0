package com.jiuxian.shedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Author: jiuxian
 * Date: 2019-01-14 22:49:00
 * Comment:
 */


//@Service
public class ScheduleService {

    @Async
    @Scheduled(fixedDelay = 2000)
    public void scheduleTest1() throws InterruptedException {
        System.out.println("scheduleTest1 Start.>>" + new Date().toLocaleString());
        Thread.sleep(6000);
        System.out.println("scheduleTest1 End.>>" + new Date().toLocaleString());
    }

    //@Async
    //@Scheduled(fixedRate = 2000)
    public void scheduleTest2() throws InterruptedException {
        System.out.println("scheduleTest2 Start.>>" + new Date().toLocaleString());
        Thread.sleep(6000);
        System.out.println("scheduleTest2  End.>>");
    }

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void scheduleTest3(){
        System.out.println(">>>");
    }

/*
    @Scheduled(fixedRate = 5000, initialDelay = 1000)
    public void scheduleTest3() throws InterruptedException {
        System.out.println("scheduleTest2 fixedRate Start.>>");
        Thread.sleep(6000);
        System.out.println("scheduleTest2 fixedRate End.>>");
    }
*/

}
