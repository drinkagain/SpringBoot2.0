package com.jiuxian.core.dao;

public class OrderBy {
    private OrderEnum orderEnum;
    private String fileName;

    public OrderBy(OrderEnum orderEnum, String fileName) {
        this.orderEnum = orderEnum;
        this.fileName = fileName;
    }

    public OrderEnum getOrderEnum() {
        return orderEnum;
    }

    public String getFileName() {
        return fileName;
    }
}
