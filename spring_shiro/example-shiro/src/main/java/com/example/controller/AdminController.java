package com.example.controller;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    /**
     * RequiresRoles 这个注解标签由shiro提供 表示访问这个方法需要有什么权限才能进行访问
     * 属性：value 和 logical
     * value：用于指定访问时所需要的一个或者多个角色
     * logical：表示当前用户必须拥有多个角色才可以访问
     * RequiresPermissions 这个注解标签用于判断当前用户是否有指定的权限与RequiresRoles匹配
     * value：用于指定访问时所需要的一个或者多个权限
     * @return
     */
    @RequiresRoles(value = "admin")
    @RequiresPermissions(value = {"admin:add"})
    @RequestMapping("/admin/add")
    @ResponseBody
    public String AdminAdd(){
        System.out.println("添加管理员");
        return "123";
    }
    @RequiresRoles(value = "admin")
    @RequiresPermissions(value = {"admin:delete"})
    @RequestMapping("/admin/delete")
    @ResponseBody
    public String AdminDelete(){
        System.out.println("删除管理员");
        return "321";
    }
}
