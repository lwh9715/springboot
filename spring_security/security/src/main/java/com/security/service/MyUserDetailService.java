package com.security.service;

import com.security.bean.Admin;
import com.security.mapper.AdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {
    public static Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    AdminMapper adminMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminMapper.selectByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("查无此人！");
        }
        return admin;
    }
}
