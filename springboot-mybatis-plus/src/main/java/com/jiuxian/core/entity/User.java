package com.jiuxian.core.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private String name;
    private Integer age;
    private String email;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;
}
