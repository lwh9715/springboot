package com.shiro.bean;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

    private String id;
    private String name;

    //定义权限的集合
    private List<Permission> permission;

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

    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }
}
