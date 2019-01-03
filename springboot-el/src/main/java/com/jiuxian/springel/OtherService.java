package com.jiuxian.springel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Author: jiuxian
 * Date: 2019-01-03 22:25:00
 * Comment:
 */


@Service
public class OtherService {

    @Value("测试其它类的属性")
    private String another;

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }
}
