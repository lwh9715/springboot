package com.security.controller;

import com.security.bean.User;
import com.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping({"/","index"})
    public String index(){
        return "index";
    }


    @RequestMapping({"/login","logout"})
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "doLogin")
    public String login(String username,String password, Map<String, Object> map, Model model){
        try {
            User login = userService.findByUsernameLogin(username, password);
            if (!StringUtils.isEmpty(login)) {
                model.addAttribute("msg",login.getUsername());
                return "index";
            }else {
                //登录失败
                map.put("msg", "用户名或密码错误,请重新输入");
                return "login";
            }
        }catch (Exception e){
            System.out.println("出现异常");
        }
        return null;
    }
}
