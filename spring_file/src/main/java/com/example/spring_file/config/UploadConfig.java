package com.example.spring_file.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @描述 : 上传文件配置类
 * @Author : 梁文辉
 * @Date: 2021-05-01 11:39
 */
@Configuration
public class UploadConfig {
    @Bean
    public MultipartConfigElement getMultipartConfig(){
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        //#设置支持单个文件上传大小限制
        multipartConfigFactory.setMaxFileSize("10MB");
        //设置总体大小请求 or 设置最大的请求文件大小
        multipartConfigFactory.setMaxRequestSize("100MB");
        //设置上传的临时目录
        multipartConfigFactory.setLocation("/");
        return multipartConfigFactory.createMultipartConfig();
    }
}
