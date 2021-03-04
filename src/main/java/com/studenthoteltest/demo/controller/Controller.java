package com.studenthoteltest.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
//    @Autowired
//    private ResidentsRepository residentsRepository;

    @GetMapping("/")
    public String first(Map<String,Object>model){
        return "index";
    }
//    @GetMapping("/main")
//    public String main(Map<String,Object>model){
//        return "main";
//    }
//
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model, String error, String logout) {
//        if (error != null) {
//            model.addAttribute("error", "Your username and password is invalid.");
//        }
//        if (logout != null) {
//            model.addAttribute("message", "You have been logged out successfully.");
//        }
//        return "login";
//    }
}
