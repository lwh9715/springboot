package com.example.service;

import com.example.bean.Permission;
import com.example.bean.User;

import java.util.List;

public interface UserService {

    //注册用户方法
    public void register(User User);

    //根据用户名查询业务方法
    User findByUsername(String username);

    //根据角色查询业务方法
    User findRoleByUsername(String username);

    //根据角色id查询权限方法
    List<Permission> findPermissionByRoleId(String id);

}
