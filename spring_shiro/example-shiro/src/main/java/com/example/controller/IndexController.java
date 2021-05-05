package com.example.controller;

import com.example.bean.User;
import com.example.service.UserService;
import com.example.util.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    //跳转到主界面
    @RequestMapping({"/","index"})
    public String index(){
        return "index";
    }

    //跳转到登陆页面
    @RequestMapping("loginView")
    public String login() {
        return "login";
    }

    //跳转到注册页面
    @RequestMapping("registerView")
    public String register() {
        return "register";
    }

    //跳转到异常页面
    @RequestMapping("/noAuth")
    public String noAuth() {
        return "noAuth";
    }

    /**
     * 退出登录
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("登出显示板");
        subject.logout();//退出用户
        return "/login";
    }

    /**
     * 用户注册
     */
    @RequestMapping("register")
    public String register(User user) {
        try {
            userService.register(user);
            return "redirect:/loginView";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/loginView";
        }
    }


    /**
     * 图片验证
     */
    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse httpServletResponse) throws IOException {
        //生成验证码
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //验证码放入session中
        session.setAttribute("verifyCode",verifyCode);
        //验证码存入图片
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        httpServletResponse.setContentType("image/png");
        VerifyCodeUtils.outputImage(200,40,outputStream,verifyCode);
    }


    /**
     * 用来处理身份认证
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public String doLogin(String username, String password, String verifyCode, HttpSession session, Model model) {
        //比较验证码
        String codes = (String) session.getAttribute("verifyCode");
        try {
            if (codes.equalsIgnoreCase(verifyCode)) {
                //获取当前的subject对象,利用这个对象完成登录操作
                Subject subject = SecurityUtils.getSubject();
                //登出，进入这个请求一定要执行登入功能，因此先执行登出操作，否则会有shiro缓存导致不能重新登录
                subject.logout();
                System.out.println("登录显示板");
                //创建用户认证的身份令牌，并设置从页面中传递过来的账号和密码
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                /**
                 *指定登登录，会自动调用我们Realm对象中的认证方法
                 */
                subject.login(token);
                return "redirect:/index";
            }else {
                model.addAttribute("msg","验证码错误");
                return "login";
            }
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
            }catch (AuthenticationException e) {
                //所有认证异常抛出
                System.out.println("其它异常抛出");
                model.addAttribute("msg", "认证失败，重新登陆吧");
                return "login";
        }
    }
}
