package com.studenthoteltest.demo.controller;


import com.studenthoteltest.demo.dao.model.Users;
import com.studenthoteltest.demo.dao.model.other.Role;
import com.studenthoteltest.demo.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Users users, Map<String,Object> model){
        Users userFromDb = userRepository.findByUsername(users.getUsername());
        if(userFromDb != null){
            model.put("massage","User exists");
            return "registration";
        }
        users.setActive(true);
        users.setRoles(Collections.singleton(Role.USER));
        userRepository.save(users);

        return "redirect:/login";
    }

}
