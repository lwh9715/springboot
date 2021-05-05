package com.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoShiroApplication.class, args);
        System.out.println("打印一句话...");
    }

}
