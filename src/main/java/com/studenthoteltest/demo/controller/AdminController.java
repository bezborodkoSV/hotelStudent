package com.studenthoteltest.demo.controller;


import com.studenthoteltest.demo.dao.model.Residents;
import com.studenthoteltest.demo.service.RoomService;
import com.studenthoteltest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

@Controller
//
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;


    @GetMapping("/admin")
    private String userList(Model model,
                            @RequestParam (value = "facultyForFilter", required = false) String facultyForFilter,
                            @RequestParam(value = "groupInForFilter",required = false)String groupInForFilter,
                            @RequestParam(value = "surnameForFilter",required = false)String surnameForFilter) {
        model.addAttribute("unverifiedUsers", userService.unverifiedUsers());
        model.addAttribute("studentUsers", userService.studentUsers());
        if (facultyForFilter!=null) {
            model.addAttribute("studentUsers",userService.studentUsersListFilterByFaculty(facultyForFilter));
        }
        if (groupInForFilter!=null) {
            model.addAttribute("studentUsers",userService.studentUsersListFilterByGroupIn(groupInForFilter));
        }
        if (surnameForFilter!=null) {
            model.addAttribute("studentUsers",userService.studentUsersListFilterBySurname(surnameForFilter));
        }
        return "admin";
    }

    @GetMapping("/admin/gt/{userId}")
    private String  gtUser(@PathVariable("userId") Long userId, Model model) {

        model.addAttribute("allUser", userService.userList(userId));
        return "admin";
    }

    @PostMapping("/admin")
    private String  acceptRejectUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        userService.acceptRejectAccount(action,userId);
        return "redirect:/admin";
    }















// user menegment

    @GetMapping("/controlResidents")
    public String showAllResident(Model model,
                                  @RequestParam(value = "numberRoomForFilter",required = false) Long numberRoomForFilter,
                                  @RequestParam(value = "numberFloorForFilter",required = false) Short numberFloorForFilter,
                                  @RequestParam(value = "facultyForFilter",required = false) String facultyForFilter,
                                  @RequestParam(value = "groupInForFilter",required = false) String groupInForFilter,
                                  @RequestParam(value = "surnameForFilter",required = false) String surnameForFilter){
        roomService.checkTheNumberOfFreeSeats();
        model.addAttribute("allStudent",userService.studentList());
        if (numberRoomForFilter != null) {
            model.addAttribute("allStudent",userService.residentsListFilterByRoom(numberRoomForFilter));
        }
        if (numberFloorForFilter != null) {
            model.addAttribute("allStudent",userService.residentsListFilterByFloor(numberFloorForFilter));
        }
        if (facultyForFilter != null) {
            model.addAttribute("allStudent",userService.residentsListFilterByFaculty(facultyForFilter));
        }
        if (groupInForFilter != null) {
            model.addAttribute("allStudent",userService.residentsListFilterByGroupIn(groupInForFilter));
        }
        if (surnameForFilter != null) {
            model.addAttribute("allStudent",userService.residentsListFilterBySurname(surnameForFilter));
        }
        return "controlResidents";
    }



    //Warning1
    @PostMapping("/controlResidents")
    public String controlResident(String action, Long residentId, Long numberRoom, RedirectAttributes redirectAttributes){
//        if (roomService.fullRoom(numberRoom)){
//            redirectAttributes.addFlashAttribute("roomFulError","Удостовертесь что в комнате есть места");
//            return "redirect:/controlResidents";
//        }
        if (!userService.moveOutMoveTo(action, residentId, numberRoom)){
            redirectAttributes.addFlashAttribute("roomError","Такої кімнати не існує");
            return "redirect:/controlResidents";
        }
        userService.moveOutMoveTo(action,residentId,numberRoom);
        return "redirect:/controlResidents";
    }

}
