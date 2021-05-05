package com.security.service;

import com.security.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    //获取用户名登陆
    User findByUsernameLogin(@Param("username")String username,@Param("password")String password);

    /**
     * UserDetailsService获取用户名登陆
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(@Param("username")String username);

}
