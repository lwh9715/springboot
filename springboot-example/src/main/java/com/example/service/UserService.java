package com.example.service;

import com.example.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    //根据用户名和密码查询数据库进行登陆
    User findByUser(@Param("username")String username,@Param("password")String password);


    //测试事务 定金案例
    void moneyMessage();

}
