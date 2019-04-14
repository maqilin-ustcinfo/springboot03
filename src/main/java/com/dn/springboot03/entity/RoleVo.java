package com.dn.springboot03.entity;

import java.util.Set;

public class RoleVo {
    private Integer roleId;
    private String roleName;
    Set<Perssion> perssionSet;

    public Set<Perssion> getPerssionSet() {
        return perssionSet;
    }

    public void setPerssionSet(Set<Perssion> perssionSet) {
        this.perssionSet = perssionSet;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
