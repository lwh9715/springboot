package org.relax.config;

import org.relax.auth.util.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName : InterceptorConfig
 * @Description : 拦截器配置类
 * @Author : 梁文辉
 * @Date: 2021-04-11 14:28
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")     //设置拦截的接口
                .excludePathPatterns("/user/**","/api/**");   //设置放行的接口
    }
}
