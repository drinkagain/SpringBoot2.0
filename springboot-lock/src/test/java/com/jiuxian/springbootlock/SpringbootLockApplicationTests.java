package com.jiuxian.springbootlock;

import com.jiuxian.springbootlock.common.LockUtil;
import com.jiuxian.springbootlock.redis.lock.RedisDistributedLock;
import com.jiuxian.springbootlock.redisson.DistributedLocker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLockApplicationTests {

    @Autowired
    private RedisDistributedLock redisDistributedLock;
    @Autowired
    private DistributedLocker distributedLocker;


    @Before
    public void hot() {
        for (int i = 0; i < 5000; i++) {
            System.out.println(i);
            redisDistributedLock.lock("ABC");
            redisDistributedLock.releaseLock("ABC");
        }
    }

    @Test
    public void testLock() throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(5000);
        int threadCount = 5000;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            pool.execute(() -> {
                int i1 = new Random().nextInt(5);
                String key = "ABC" + i1;
                boolean lock = redisDistributedLock.lock(key);
                if (!lock){
                    atomicInteger.incrementAndGet();
                }
                sleep();
                redisDistributedLock.releaseLock(key);
                countDownLatch.countDown();
                System.out.println(key + finalI);
            });
        }
        countDownLatch.await();
        pool.shutdown();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(atomicInteger.get());
    }

    private static void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
