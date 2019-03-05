package com.jiuxian;

import com.jiuxian.publisher.DemoPublisher;
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
    public void publisherTest() {
        demoPublisher.publish(1L, "成功了！");
    }

}
