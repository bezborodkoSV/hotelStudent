package com.studenthoteltest.demo.controller;

import com.studenthoteltest.demo.dao.model.ApplicationsForAccommodation;
import com.studenthoteltest.demo.service.ApplicationsForAccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ApplicationsForAccommodationControoler {
    @Autowired
    private ApplicationsForAccommodationService applicationsForAccommodationService;

    @GetMapping("/applicationsForAccommodation")
    public String applicationsForAccommodation( Model model){
//        Principal principal,
        model.addAttribute("applicationsForAccommodationForm",new ApplicationsForAccommodation());

        return "applicationsForAccommodation";
    }

    @PostMapping("/applicationsForAccommodation")
    public String saveApplicationsForAccommodation(@ModelAttribute("applicationsForAccommodationForm")ApplicationsForAccommodation applicationsForAccommodation,
                                                   @RequestParam long numberRoom,
                                                   BindingResult bindingResult, Model model,
                                                   Principal principal){
        if(bindingResult.hasErrors()){
            return "applicationsForAccommodation";
        }

        if (!applicationsForAccommodationService.save(applicationsForAccommodation, principal.getName(), numberRoom)){

            model.addAttribute("nameError","Вы превысили количество заявок(больше 3)");
        }
        applicationsForAccommodationService.save(applicationsForAccommodation, principal.getName(), numberRoom);

        return "redirect:/news";
    }

}
