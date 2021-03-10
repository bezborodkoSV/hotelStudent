package com.studenthoteltest.demo.controller;

import com.studenthoteltest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private UserService userService;
//    @Autowired
//    private ResidentsRepository residentsRepository;

//    @GetMapping("/")
//    public String first(Map<String,Object>model){
//        return "index";
//    }

//
//    @GetMapping("/main")
//    public String main(Map<String,Object>model){
//        return "main";
//    }
}
