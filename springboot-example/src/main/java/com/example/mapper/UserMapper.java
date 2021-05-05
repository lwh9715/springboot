package com.example.mapper;

import com.example.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    //根据id查询返回
    @Select("SELECT * FROM admin WHERE username=#{username} AND password=#{password}")
    User findByUser(@Param("username")String username,@Param("password")String password);

    //支付定金 p_price:支付的定金额度 p_id：支付人唯一标识符id
    @Update("UPDATE product SET p_price=p_price-#{p_price} WHERE p_id=#{p_id}")
    void reduceMoney(@Param("p_price")String p_price ,@Param("p_id")Integer id);
    //收取定金
    @Update("UPDATE product SET p_price=p_price+#{p_price} WHERE p_id=#{p_id}")
    void addMoney(@Param("p_price")String p_price,@Param("p_id")Integer id);
}
