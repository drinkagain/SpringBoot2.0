package com.jiuxian.base.user.entity;

import com.jiuxian.base.role.entity.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ts_user", uniqueConstraints = @UniqueConstraint(columnNames = { "ACCOUNT" }))
public class User extends Employee {
    private String password;
    private String status;
    private String credentialsSalt;
    @ManyToMany
    @JoinTable(name = "ts_user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roleSet;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public String getCredentialsSalt() {
        return credentialsSalt;
    }

    public void setCredentialsSalt(String credentialsSalt) {
        this.credentialsSalt = credentialsSalt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
