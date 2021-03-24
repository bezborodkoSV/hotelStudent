package com.studenthoteltest.demo.controller;

import com.studenthoteltest.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizedUserController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/news")
    public String first(Model model){
        model.addAttribute("freeRoomForAuthorized",roomService.roomFreeList());
        return "news";
    }
}
