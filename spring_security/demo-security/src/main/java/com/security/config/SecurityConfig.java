package com.security.config;

import com.security.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 开启安全机制
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BackdoorAuthenticationProvider backdoorAuthenticationProvider;
    @Autowired
    UserServiceImpl userService;

    /**
     * // 配置用户及其对应的角色
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .withUser("admin").password(new BCryptPasswordEncoder().encode("pwd")).roles("USER");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .withUser("user").password(new BCryptPasswordEncoder().encode("pwd")).roles("ADMIN");
        //将自定义验证类注册进去
        auth.authenticationProvider(backdoorAuthenticationProvider);
        //加入数据库验证类，下面的语句实际上在验证链中加入了一个DaoAuthenticationProvider
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * // 开启 HttpSecurity 配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests()就是在进行请求的权限配置
        http.authorizeRequests().
                //所有用户都可以访问/的URL
                antMatchers("/").permitAll()
                //指定了登录页的URL，并允许所有的用户(包括没认证的)访问登录页，
                // formLogin().permitAll()方法允许所有用户访问这个URL
                .and().formLogin().loginPage("/doLogin").permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logout");
    }
}
