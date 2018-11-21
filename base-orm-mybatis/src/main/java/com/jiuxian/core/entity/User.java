package com.jiuxian.core.entity;

import lombok.Data;

@Data
public class User extends BaseEntity {
    private String uid;
    private String name;
    private Integer age;
    private String email;
}
