package com.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/blog")
public class BlogController {


    @RequestMapping("/hello")
    public String hi(){
        return "{''message01':'Springboot你好','ajax'}";
    }

    @RequestMapping("/{id}")
    public ModelAndView show(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id",id);
        //相当于return返回页面功能
        modelAndView.setViewName("blog");
        return modelAndView;
    }

    @RequestMapping("/query")
    public ModelAndView query(@RequestParam(value = "q",required = false)String q){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("q",q);
        //相当于return返回页面功能
        modelAndView.setViewName("query");
        return modelAndView;
    }
}
