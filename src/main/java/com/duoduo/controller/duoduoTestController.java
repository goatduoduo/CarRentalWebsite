package com.duoduo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class duoduoTestController {
    @GetMapping("/sayHello")
    public String sayHello(){
        return "hello";
    }
    @RequestMapping("/hello")
    public void hello(){
        System.out.println("hello duoduo");
    }
}
