package com.studenthoteltest.demo.controller;


import com.studenthoteltest.demo.dao.model.Residents;
import com.studenthoteltest.demo.dao.model.Users;
import com.studenthoteltest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new Users());
        return "registration";
    }


//    User registration
    @PostMapping("/registration")
    public String addUser(@Valid @ModelAttribute ("userForm") Users userForm,
                          BindingResult bindingResult, Model model) throws Exception{

        if(bindingResult.hasErrors()){
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        userService.saveUser(userForm);
        return "redirect:/";
    }
    //      User registration




//    Resident add

    @GetMapping("/residentAdd")
    public String resident(Model model, Principal principal) {
        model.addAttribute("residentForm", new Residents());
        if(!userService.checkResidentsByUser(principal.getName())){
            return "redirect:/";
        }

        return "residentAdd";
    }


@PostMapping("residentAdd")
    public String residentAdd(@ModelAttribute("residentForm")@Valid Residents residentForm,
                              BindingResult bindingResult, Model model,
                              Principal principal){

    if(bindingResult.hasErrors()){
        return "residentAdd";
    }
    if (!userService.saveResident(residentForm, principal.getName())){
        model.addAttribute("residentError","Такой пользователь уже существует");
        return "residentAdd";
    }
    userService.saveResident(residentForm, principal.getName());
        return "redirect:/";
}
//Resident add

}
