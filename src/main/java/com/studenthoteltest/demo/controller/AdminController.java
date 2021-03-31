package com.studenthoteltest.demo.controller;


import com.studenthoteltest.demo.service.RoomService;
import com.studenthoteltest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoomService roomService;


    @GetMapping("/admin")
    private String userList(Model model) {
        model.addAttribute("unverifiedUsers", userService.unverifiedUsers());
        model.addAttribute("studentUsers",userService.studentUsers());
        return "admin";
    }

    @GetMapping("/admin/gt/{userId}")
    private String  gtUser(@PathVariable("userId") Long userId, Model model) {

        model.addAttribute("allUser", userService.userList(userId));
        return "admin";
    }

    @PostMapping("/admin")
    private String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        userService.acceptRejectAccount(action,userId);
        return "redirect:/admin";
    }















// user menegment

    @GetMapping("/controlResidents")
    public String showAllResident(Model model){
        roomService.checkTheNumberOfFreeSeats();
        model.addAttribute("allStudent",userService.studentList());
        return "controlResidents";
    }



    //Warning1
    @PostMapping("/controlResidents")
    public String controlResident(String action, Long residentId, Long numberRoom, RedirectAttributes redirectAttributes){
//        if (roomService.fullRoom(numberRoom)){
//            redirectAttributes.addFlashAttribute("roomFulError","Удостовертесь что в комнате есть места");
//            return "redirect:/controlResidents";
//        }
        userService.moveOutMoveTo(action,residentId,numberRoom);
        return "redirect:/controlResidents";
    }

//    @PostMapping("/controlResidents")
//    public String filter(Long numberRoom,Model model){
//        System.out.println("asffdsd  "+ numberRoom);
//        if (numberRoom==null){
//            return "redirect:/controlResidents";
//        }
////        userService.residentsListByNumberRoom(numberRoom);
//        model.addAttribute("allStudent",userService.residentsListFilterByRoom(numberRoom));
//        return "controlResidents";
//    }

}
