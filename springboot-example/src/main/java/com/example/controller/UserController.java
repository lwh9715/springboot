package com.example.controller;

import com.example.bean.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    //来到登陆页面
    @RequestMapping(value = {"login","logout"})
    public String toLogin(){
        return "login";
    }

    //根据用户名和密码查询数据库进行登陆
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam String username,@RequestParam String password, Model model){
        User user = userService.findByUser(username,password);
        if (!StringUtils.isEmpty(user)){
            model.addAttribute("msg",user.getUsername());
            return "index";
        }else {
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
    }

}
