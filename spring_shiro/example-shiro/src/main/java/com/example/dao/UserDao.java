package com.example.dao;

import com.example.bean.Permission;
import com.example.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    //注册用户方法
    void save(User user);

    //根据用户名查询业务方法
    User findByUsername(String username);

    //根据角色查询业务方法
    User findRoleByUsername(String username);

    //根据角色id查询权限方法
    List<Permission> findPermissionByRoleId(String id);
}
