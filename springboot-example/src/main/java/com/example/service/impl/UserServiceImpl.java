package com.example.service.impl;

import com.example.bean.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    //根据用户名和密码查询数据库进行登陆
    @Override
    public User findByUser(String username, String password) {
        return userMapper.findByUser(username,password);
    }

    @Override
    public void moneyMessage() {
        //进行支付定金交易
        userMapper.reduceMoney("150",1);

        //定义错误
        //int money = 10/0;

        //进行收取定金交易
        userMapper.addMoney("50",2);

    }
}
