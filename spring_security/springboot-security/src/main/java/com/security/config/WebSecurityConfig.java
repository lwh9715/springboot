package com.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //拦截请求
    //定制请求的授权规则,设置哪些url允许被某种角色访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //开启自动配置的登录功能：效果：如果没有登陆，没有权限就会来到登陆页面
        //1,formLogin定义当需要用户登录时候，转到的登录页面
        //2,/重定向到/login?error表示登录失败
        http.formLogin().loginPage("/login_kungfu").failureForwardUrl("/error");
        //启用注销功能，（需要提供一个action为/logout的form）并设置注销后访问的url，这里注销后跳转到首页
        //1,访问/logout表示用户注销，清空session
        //2,注销成功会返回 Login?Logout
        //注销成功之后来到首页
        http.logout().logoutSuccessUrl("/");
        //启用rememberMe功能，将用户信息保存在cookie中
        //点击注销会删除cookie
        http.rememberMe();
    }

    //授权认证

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //Spring security 5.0中新增了多种加密方式,也改变了默认的密码格式.
        //inMemoryAuthentication表示使用基于内存的验证，还可以使用基于数据库的验证等，使用BCrypt编码对密码进行加密
        auth.
            inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .withUser("user1").password(new BCryptPasswordEncoder().encode("123")).roles("vip1")
            .and()
            .withUser("user2").password(new BCryptPasswordEncoder().encode("123")).roles("vip2")
            .and()
            .withUser("user3").password(new BCryptPasswordEncoder().encode("123")).roles("vip3")
            .and()
            .withUser("user").password(new BCryptPasswordEncoder().encode("123"))
            .roles("vip1","vip2","vip3");
    }
}
