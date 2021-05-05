package com.shiro.controller;

import com.shiro.bean.Admin;
import com.shiro.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ShiroHomeController {

    @Autowired
    AdminService adminService;

    //来到首页
    @RequestMapping({"/","/index"})
    public String index() {
        return "index";
    }

    //跳转到异常页面
    @RequestMapping("/noAuth")
    public String noAuth() {
        return "noAuth";
    }

    //若在shiroConfig配置logout这里就不再生效了：先保留
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //登出当前账户 清空shiro当前账户的缓存，否则无法登录
        subject.logout();
        System.out.println("登出操作...");
        return "redirect:/login";
    }

    //来到注册页面
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 用户注册
     * @return
     */
    //接收到url请求地址register（post请求）
    @PostMapping("register")
    public String doRegister(Admin admin){
        try {
            adminService.register(admin);
            return "redirect:/login";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/register";
        }
    }

    //来到登陆页面
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    //接收到url请求地址login（post请求）
    @PostMapping("/login")
    public String doLogin(String username, String password, Model model) {
        //获取当前的subject对象,利用这个对象完成登录操作
        Subject subject = SecurityUtils.getSubject();
        //登出，进入这个请求一定要执行登入功能，因此先执行登出操作，否则会有shiro缓存导致不能重新登录
        subject.logout();
        //检查是否认证
        if (!subject.isAuthenticated()) {
            System.out.println("登录显示板");
            //创建用户认证的身份令牌，并设置从页面中传递过来的账号和密码
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                /**
                 *指定登登录，会自动调用我们Realm对象中的认证方法
                 */
                subject.login(token);
                /**
                 * 这里设置的异常信息回显页面
                 */
            }catch (UnknownAccountException e) {
                //账号认证失败抛出
                model.addAttribute("msg", "账号认证失败，重新登陆吧");
                return "login";
            }catch (IncorrectCredentialsException e){
                //密码认证失败抛出
                model.addAttribute("msg", "密码认证失败，重新登陆吧");
                return "login";
            }catch (AuthorizationException e){
                model.addAttribute("msg","权限不足，请联系管理员开通");
                return "login";
            }catch (AuthenticationException e) {
                //所有认证异常抛出
                System.out.println("其它异常抛出");
                model.addAttribute("msg", "认证失败，重新登陆吧");
                return "login";
            }
        }
        return "/index";
    }

}
