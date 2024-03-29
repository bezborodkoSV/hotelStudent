package com.studenthoteltest.demo.controller;

import com.studenthoteltest.demo.dao.model.ApplicationsForAccommodation;
import com.studenthoteltest.demo.service.ApplicationsForAccommodationService;
import com.studenthoteltest.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class ApplicationsForAccommodationControoler {
    @Autowired
    private ApplicationsForAccommodationService applicationsForAccommodationService;
    @Autowired
    private RoomService roomService;

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
        if (!roomService.presenceOfSuchRoom(numberRoom)){
            model.addAttribute("roomError","Такої кімнати не існує");
            return "applicationsForAccommodation";
        }
        if (!applicationsForAccommodationService.chekSeatAvailability(numberRoom)){
            model.addAttribute("roomError","У кімнаті нема місць, перегляньте і виберіть іншу кімнату");
            return "applicationsForAccommodation";
        }
        if (!applicationsForAccommodationService.save(applicationsForAccommodation,principal.getName(),numberRoom)){
            model.addAttribute("applicationError","Ви перевищили кількість заявок (5 штук)");
            return "applicationsForAccommodation";
        }
        applicationsForAccommodationService.save(applicationsForAccommodation, principal.getName(), numberRoom);

        return "redirect:/news";
    }

    @GetMapping("/applications")
    public String applications(Model model){
        model.addAttribute("unverifiedApplications", applicationsForAccommodationService.unverifiedApplicationList());
        model.addAttribute("deflectApplications",applicationsForAccommodationService.deflectApplicationList());
        model.addAttribute("acceptApplications",applicationsForAccommodationService.acceptApplicationList());
        return "applications";
    }

    @GetMapping("/applications/gt/{applicationId}")
    private String  gtFloor(@PathVariable("roomId") Long applicationId, Model model) {
        model.addAttribute("allRoom", applicationsForAccommodationService.applicationList(applicationId));
        return "applications";
    }

    @PostMapping("/applications")
    private String  makingDecisionOnApplication(@RequestParam(required = true, defaultValue = "" ) Long applicationId,
                               @RequestParam(required = true, defaultValue = "" ) String action) {
            applicationsForAccommodationService.makingDecisionOnApplication(applicationId, action);
        return "redirect:/applications";
    }

}
