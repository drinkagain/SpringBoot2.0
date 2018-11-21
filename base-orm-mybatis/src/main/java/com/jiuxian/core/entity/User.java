package com.jiuxian.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @TableId
    private String uid;
    private String name;
    private Integer age;
    private String email;
}
