package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello huachenyu";
    }

    @GetMapping("/queryByName")
    public String queryByName(@RequestParam(name = "name",defaultValue = "hello huachenyu",required = false) String name, Model model){
        model.addAttribute("name", name);
        return "query";
    }

}
