package com.security.mapper;

import com.security.bean.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    /**
     * 从数据库中查询用户
     * @param username
     * @return
     */
    @Select("SELECT username,password FROM admin WHERE username = #{username}")
    Admin selectByUsername(@Param("username") String username);
}
