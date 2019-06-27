package com.jiuxian.springbootlock;

import com.jiuxian.springbootlock.common.LockUtil;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLockApplicationTests {

    @Before
    public void hot() {
        for (int i = 0; i < 5000; i++) {
            System.out.println(i);
            LockUtil.lock("ABC");
            LockUtil.releaseLock("ABC");
        }
    }
}
