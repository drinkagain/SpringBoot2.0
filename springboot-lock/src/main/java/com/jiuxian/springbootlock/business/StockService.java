package com.jiuxian.springbootlock.business;

import com.jiuxian.springbootlock.common.annotation.DistributedLock;
import org.springframework.stereotype.Component;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-27 12:50:29
 * *
 * @description:
 **/
@Component
public class StockService {

    @DistributedLock(parameterKey = "commodityId")
    public void distributedLockParameterKey(String commodityId, int qty) {
        System.out.println("key:" + commodityId + "<<<<<<distributedLockParameterKey>>>>");
    }

    @DistributedLock(parameterKeyIndex = 0)
    public void distributedLockParameterKeyIndex(String commodityId, int qty) {
        System.out.println("key:" + commodityId + "<<<<<<distributedLockParameterKeyIndex>>>>");
    }

    @DistributedLock(methodKey = "stockLockKey")
    public void distributedLockMethod(Order order) {
        System.out.println("key:" + order.getCommodityId() + "<<<<<<distributedLockMethod>>>>");
    }

    public String stockLockKey(Order order) {
        return order.getCommodityId();
    }
}
