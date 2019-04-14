package com.dn.springboot03.entity;

import java.util.Date;
import java.util.Set;

public class User{

    private String id;

    private String name;

    private String password;

    private Date bithDay;
    
    Set<RoleVo> roleVos;

    public Set<RoleVo> getRoleVos() {
        return roleVos;
    }

    public void setRoleVos(Set<RoleVo> roleVos) {
        this.roleVos = roleVos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBithDay() {
        return bithDay;
    }

    public void setBithDay(Date bithDay) {
        this.bithDay = bithDay;
    }
}
