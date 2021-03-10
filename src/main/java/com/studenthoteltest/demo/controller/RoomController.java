package com.studenthoteltest.demo.controller;

import com.studenthoteltest.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/room")
    public String room(Model model){
        model.addAttribute("allRoom",roomService.allRooms());
        return "room";
    }

}
