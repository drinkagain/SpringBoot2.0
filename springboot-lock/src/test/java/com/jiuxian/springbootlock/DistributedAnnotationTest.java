package com.jiuxian.springbootlock;

import com.jiuxian.springbootlock.business.Order;
import com.jiuxian.springbootlock.business.StockService;
import com.jiuxian.springbootlock.common.LockUtil;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-27 13:01:29
 * *
 * @description: 分步式锁 注解测试
 **/
public class DistributedAnnotationTest extends SpringbootLockApplicationTests {

    @Resource
    private StockService stockService;

    @Test
    public void distributedLockParameterKeyTest() {
        String commodityId = "A";
        stockService.distributedLockParameterKey(commodityId, 3);
    }

    @Test
    public void distributedLockParameterKeyIndexTest() {
        String commodityId = "B";
        stockService.distributedLockParameterKeyIndex(commodityId, 3);
    }

    @Test
    public void distributedLockMethodTest() {
        Order order = Order.builder().commodityId("C").qty(3).build();
        stockService.distributedLockMethod(order);
    }

}
