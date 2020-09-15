package com.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class KungfuController {

    private final String PREFIX = "pages/";

    @GetMapping("/")
    public String login(){
        return "index";
    }

    @GetMapping("/login")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/level1/{path}")
    public String level_1(@PathVariable("path") String path){
        return PREFIX+"level1/"+path;
    }
    @GetMapping("/level2/{path}")
    public String level_2(@PathVariable("path") String path){
        return PREFIX+"level2/"+path;
    }
    @GetMapping("/level3/{path}")
    public String level_3(@PathVariable("path") String path){
        return PREFIX+"level3/"+path;
    }
}
