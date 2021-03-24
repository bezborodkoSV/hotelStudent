package com.studenthoteltest.demo.controller;


import com.studenthoteltest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//
public class AdminController {
    @Autowired
    private UserService userService;


    @GetMapping("/admin")
    private String userList(Model model) {
       model.addAttribute("allResidents", userService.allResidents());
        return "admin";
    }

    @PostMapping("/admin")
    private String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long residentUserId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(residentUserId);

        }
        return "redirect:/admin";
    }


    @GetMapping("/admin/gt/{residentId}")
    private String  gtUser(@PathVariable("residentId") Long residentId, Model model) {
        model.addAttribute("allResidents", userService.userList(residentId));
        return "admin";
    }



}
