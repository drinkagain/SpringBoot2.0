package com.jiuxian.base.permission.entity;

import com.jiuxian.base.role.entity.Role;
import com.jiuxian.entity.AuditEntity;

import javax.persistence.*;

@Entity
@Table(name = "ts_permission", uniqueConstraints = @UniqueConstraint(columnNames = {"code"}))
public class Permission extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long tsRoleId;
    private String code;
    private String name;
    private String remark;
    @ManyToOne
    @JoinColumn(name = "id", nullable = false,insertable = false,updatable = false)
    private Role role;

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

    public Long getTsRoleId() {
        return tsRoleId;
    }

    public void setTsRoleId(Long tsRoleId) {
        this.tsRoleId = tsRoleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
