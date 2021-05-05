package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoShiroApplicationTests {

    @Test
    public void text() {
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("user","123");
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                System.out.println("登录成功!!!");
                //测试是否有这一角色
                if (subject.hasRole("root")){
                    //测试用户是否具有某一行为
                    if (subject.isPermitted("update:while")){
                        System.out.println("测试用户user,具有行为update");
                    } if(subject.isPermitted("delete:*")){
                        System.out.println("测试用户user具有行为delete");
                    }
                    System.out.println("匹配角色-user");
                }else {
                    System.out.println("角色不匹配");
                }
            }
        }catch (UnknownAccountException e){
            System.out.println("账户不匹配"+token.getPrincipal()+"UnknownAccountException异常");
        }catch (IncorrectCredentialsException e){
            System.out.println("账户匹配,密码不匹配"+token.getPrincipal()+"IncorrectCredentialsException异常");
        }catch (LockedAccountException e){
            System.out.println("用户被锁定异常"+token.getPrincipal());
        }catch (AuthenticationException all){
            System.out.println("登录异常");
        }
    }
    @Test
    public void text_01() {
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("root","root");
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                System.out.println("登录成功!!!");
            }
        }catch (UnknownAccountException e){
            System.out.println("账户不匹配"+token.getPrincipal()+"UnknownAccountException异常");
        }catch (IncorrectCredentialsException e){
            System.out.println("账户匹配密码不匹配"+token.getPrincipal()+"IncorrectCredentialsException异常");
        }catch (LockedAccountException e){
            System.out.println("用户被锁定异常"+token.getPrincipal());
        }catch (AuthenticationException all){
            System.out.println("登录异常");
        }
    }
}
