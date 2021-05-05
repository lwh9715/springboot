package com.jpa.controller;

import com.jpa.bean.User;
import com.jpa.mapper.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller//控制器
public class UserController {
    @Autowired
    private UserCrudRepository userCrudRepository;

    //@RequestParam表示传入User的构造器中
    @GetMapping(path = "/add")
    @ResponseBody
    public String addNewUser(@RequestParam String firstname,@RequestParam String lastname){
        User user = new User(firstname,lastname);
        userCrudRepository.save(user);
        return "Saved";
    }

}
