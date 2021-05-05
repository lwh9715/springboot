package com.security.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/info")
    @ResponseBody
    public String productInfo(){
        return " some product info ";
    }
}
