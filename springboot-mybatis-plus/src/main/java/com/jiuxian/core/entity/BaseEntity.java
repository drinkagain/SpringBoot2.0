package com.jiuxian.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author zejun.liu
 */
@Getter
@Setter
public class BaseEntity extends AbstractEntity {

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
