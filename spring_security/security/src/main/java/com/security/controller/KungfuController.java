package com.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class KungfuController {

    private static Logger logger=LoggerFactory.getLogger(KungfuController.class);

    private final String PREFIX = "pages/";

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @GetMapping("/login")
    public String welcome() {

        return "login";
    }

    @GetMapping("/level1/{path}")
    public String top1(@PathVariable("path") String path){
        return PREFIX + "level1/" + path;
    }
    @GetMapping("/level2/{path}")
    public String top2(@PathVariable("path") String path){
        return PREFIX + "level2/" + path;
    }
    @GetMapping("/level3/{path}")
    public String top3(@PathVariable("path") String path){
        return PREFIX + "level3/" + path;
    }
    @GetMapping("/levels")
    public String top4(){
        return PREFIX + "level1/";
    }
}
