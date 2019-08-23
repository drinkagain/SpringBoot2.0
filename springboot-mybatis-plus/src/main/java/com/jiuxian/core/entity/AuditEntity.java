package com.jiuxian.core.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: zejun.liu
 * *
 * @date: 2019/8/22 20:04
 * *
 * @description:
 */
@Getter
@Setter
public class AuditEntity extends BaseEntity {

    private Integer createUser;
    private Integer updateUser;
}
