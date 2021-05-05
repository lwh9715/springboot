package com.security.mapper;

import com.security.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    //获取用户名登陆
    User findByUsername(@Param("username")String username,@Param("password")String password);

    /**
     * UserDetailsService获取用户名登陆
     * @param username
     * @return
     */
    User loadUserByUsername(@Param("username")String username);

}
