package com.duoduo.controller;

import com.duoduo.model.User;
import com.duoduo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/findAll")
    public String findAll(Model model){
        List<User> userList=userService.findAll();
        for(User user:userList){
            System.out.println("id : "+user.getID());
            System.out.println("name : "+user.getName());
        }
        return "hello";
    }
    @RequestMapping("/index.do")
    public String testindex(){
        return "/new/template";
    }
}
