package com.jiuxian.base.role.entity;

import com.jiuxian.base.permission.entity.Permission;
import com.jiuxian.base.user.entity.User;
import com.jiuxian.core.entity.AuditEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ts_role", uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class Role extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String remark;
    @ManyToMany
    @JoinTable(name = "ts_user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> userSet;
    @OneToMany
    @JoinColumn(name = "tsRoleId", nullable = false,insertable = false,updatable = false)
    private Set<Permission> permissionSet;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }
}
