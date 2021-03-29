package com.studenthoteltest.demo.controller;

import com.studenthoteltest.demo.service.FloorsService;
import com.studenthoteltest.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private FloorsService floorsService;
    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    public String index(){
//        Map<String,Object>model
        roomService.checkTheNumberOfFreeSeats();
        floorsService.checkFreeRooms();
        return "redirect:/first";
    }

    @GetMapping("/first")
    public String first(Model model){
        model.addAttribute("freeRoomForAll",roomService.roomFreeList());
        return "first";
    }

}
