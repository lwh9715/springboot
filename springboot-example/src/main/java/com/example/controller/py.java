package com.example.controller;

import com.example.bean.Python;
import com.example.service.PythonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class py {
    @Autowired
    PythonService pythonService;

    @RequestMapping("py")
    public String py(@RequestParam("keyWord") String keyWord, Model model){
        List<Python> python = pythonService.pythonUtils(keyWord);
        model.addAttribute("py",python);
        return "table";
    }
}