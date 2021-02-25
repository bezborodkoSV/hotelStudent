package com.studenthoteltest.demo.controller;

import com.studenthoteltest.demo.dao.model.Residents;
import com.studenthoteltest.demo.dao.repository.ResidentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private ResidentsRepository residentsRepository;

    @GetMapping("/")
    public String first(Map<String,Object>model){
        Iterable<Residents> residents = residentsRepository.findAll();
        model.put("residents",residents);
        return "first";
    }
    @GetMapping("/main")
    public String main(Map<String,Object>model){
        return "main";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }
}
