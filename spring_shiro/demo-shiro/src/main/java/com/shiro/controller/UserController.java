package com.shiro.controller;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    /**
     * RequiresRoles 这个注解标签由shiro提供 表示访问这个方法需要有什么权限才能进行访问
     * 属性：value 和 logical
     * value：用于指定访问时所需要的一个或者多个角色
     * logical：表示当前用户必须拥有多个角色才可以访问
     * RequiresPermissions 这个注解标签用于判断当前用户是否有指定的权限与RequiresRoles匹配
     * value：用于指定访问时所需要的一个或者多个权限
     * @return
     */
    @RequiresRoles(value = "user")
    @RequiresPermissions(value = {"user:add"})
    @RequestMapping("/user/add")
    @ResponseBody
    public String UserAdd(){
        System.out.println("添加用户");
        return "添加user";
    }
    @RequiresRoles(value = "user")
    @RequiresPermissions(value = {"user:delete"})
    @RequestMapping("/user/delete")
    @ResponseBody
    public String UserDelete(){
        System.out.println("删除用户");
        return "删除user";
    }

    /**
     *配置自定义拦截异常，需要拦截UnauthorizedException异常或者ShiroException异常
     * 注意：当前shiro出现验证权限失败会抛出异常，因此需要写自定义异常处理
     */
   /* @ExceptionHandler(value = {ShiroException.class})
    public String ErrorException(Model model){
        System.out.println("ShiroException.class 异常报错");
        return "/noAuth";
    }*/
}
