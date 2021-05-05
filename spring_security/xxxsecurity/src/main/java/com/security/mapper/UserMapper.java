package com.security.mapper;

import com.security.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by wxb on 2018/10/23 0023.
 * 数据库表user的mapper类
 */
@Mapper
@Component
public interface UserMapper {
    /**
     * 从数据库中查询用户
     * @param username
     * @return
     */
    @Select("select * from admin where username = #{username}")
    User selectByUsername(@Param("username") String username);
}
