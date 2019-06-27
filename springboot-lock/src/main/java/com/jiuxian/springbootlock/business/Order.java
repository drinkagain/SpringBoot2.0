package com.jiuxian.springbootlock.business;

import lombok.Builder;
import lombok.Getter;

/**
 * @author: liuzejun
 * *
 * @email: 857591294@qq.com
 * *
 * @date: 2019-06-27 12:57:05
 * *
 * @description:
 **/
@Builder
@Getter
public class Order {

    private String commodityId;

    private Integer qty;
}
