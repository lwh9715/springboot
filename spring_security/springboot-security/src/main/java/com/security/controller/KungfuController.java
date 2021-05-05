package com.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KungfuController {

    private final String PREFIX = "pages/";

    @GetMapping("/login_kungfu")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
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
