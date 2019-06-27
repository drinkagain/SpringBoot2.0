package com.jiuxian.springbootlock;

import com.jiuxian.springbootlock.common.LockUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-27 13:04:07
 * *
 * @description:
 **/
public class LockUtilTest extends SpringbootLockApplicationTests {

    @Test
    public void testLock() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(500);

        long start = System.currentTimeMillis();

        int threadCount = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            pool.execute(() -> {
                String key = "ABC";
                LockUtil.lock(key);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignore) {
                }
                LockUtil.releaseLock(key);
                countDownLatch.countDown();
                System.out.println(finalI);
            });
        }
        countDownLatch.await();
        System.out.println(System.currentTimeMillis() - start);
    }

}
