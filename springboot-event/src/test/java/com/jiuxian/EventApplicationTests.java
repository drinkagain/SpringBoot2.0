package com.jiuxian;

import com.jiuxian.event.DemoPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventApplicationTests {

    @Resource
    private DemoPublisher demoPublisher;

    @Test
    public void publisherTest() throws InterruptedException {
        demoPublisher.publish(1L, "成功了！");

        Thread.sleep(2000);
    }

}
