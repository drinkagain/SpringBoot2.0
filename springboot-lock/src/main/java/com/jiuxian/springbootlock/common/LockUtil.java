package com.jiuxian.springbootlock.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-27 11:23:50
 * *
 * @description: 锁
 **/
@Slf4j
public class LockUtil {

    private final static String LOCK_PREV = "LOCK:";

    private final static long LOCK_EXPIRE = 5000;

    private final static int SLEEP_MILLIS = 50;

    @SuppressWarnings("unchecked")
    private final static LockService<Long> lockService = ApplicationContextHolder.getApplicationContext().getBean(LockService.class);

    public static void lock(String preKey) {
        String lockKey = LOCK_PREV + preKey;
        long start = System.currentTimeMillis();

        log.info(lockKey + ":竞争锁");
        for (; ; ) {
            boolean success = lockService.setIfAbsent(lockKey, System.currentTimeMillis());
            if (success) break;

            Long timestamp = lockService.get(lockKey);

            if (StringUtils.isEmpty(timestamp)) continue;

            if (getLock(lockKey, timestamp)) break;

            sleep();
        }
        lockService.expire(lockKey, (int) (LOCK_EXPIRE / 1000));

        log.info(lockKey + ":锁得锁，耗时：" + (System.currentTimeMillis() - start));
    }

    private static void sleep() {
        try {
            Thread.sleep(SLEEP_MILLIS);
        } catch (InterruptedException ignore) {
        }
    }


    private static boolean getLock(String key, Long timestamp) {
        if (System.currentTimeMillis() - timestamp > LOCK_EXPIRE) {
            Long oldTimestamp = lockService.getAndSet(key, System.currentTimeMillis());
            return (timestamp).equals(oldTimestamp);
        }
        return false;
    }


    public static void releaseLock(String key) {
        String lockKey = LOCK_PREV + key;

        lockService.release(lockKey);

        log.info(lockKey + " :释放锁");
    }
}
