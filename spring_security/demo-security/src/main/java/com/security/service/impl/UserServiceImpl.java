package com.security.service.impl;

import com.security.bean.User;
import com.security.mapper.UserMapper;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;

    //获取用户名登陆
    @Override
    public User findByUsernameLogin(String username,String password) {
        return userMapper.findByUsername(username,password);
    }

    /**
     * UserDetailsService获取用户名登陆
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            System.out.println("用户不存在");
        }
        return user;
    }
}
