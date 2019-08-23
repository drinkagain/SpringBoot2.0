package com.jiuxian.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: zejun.liu
 * *
 * @date: 2019/8/22 20:01
 * *
 * @description: 顶级抽象工厂类
 */
@Getter
@Setter
public abstract class AbstractEntity {

    @TableId
    private Integer id;
}
