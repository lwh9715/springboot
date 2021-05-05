package com.shiro.service;

import com.shiro.bean.Admin;
import com.shiro.bean.Permission;

import java.util.List;

public interface AdminService {
    //注册用户方法
    public void register(Admin admin);

    //根据用户名查询业务方法
    Admin findByUsername(String username);

    //根据角色查询业务方法
    Admin findRoleByUsername(String username);

    //根据角色id查询权限方法
    List<Permission> findPermissionByRoleId(String id);
}
