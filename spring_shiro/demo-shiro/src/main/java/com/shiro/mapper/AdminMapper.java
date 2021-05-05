package com.shiro.mapper;

import com.shiro.bean.Admin;
import com.shiro.bean.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    //注册用户方法
    void save(Admin admin);

    //根据用户名查询业务方法
    Admin findByUsername(String username);

    //根据角色查询业务方法
    Admin findRoleByUsername(String username);

    //根据角色id查询权限方法
    List<Permission> findPermissionByRoleId(String id);
}
