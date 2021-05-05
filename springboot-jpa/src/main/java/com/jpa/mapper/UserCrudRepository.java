package com.jpa.mapper;

import com.jpa.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCrudRepository extends JpaRepository<User,Integer> {
}
