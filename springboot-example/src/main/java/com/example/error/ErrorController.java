package com.example.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error/{httpStatus}")
    public String errorPageStatus(@PathVariable Integer httpStatus){
        switch (httpStatus){
            case 401:
            case 404: return "/error/404";
            case 500: return "/error/500";
            default:  return "/error/403";
        }
    }
}
