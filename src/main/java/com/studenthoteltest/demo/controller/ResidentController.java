package com.studenthoteltest.demo.controller;

import com.studenthoteltest.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ResidentController {
    @Autowired
    private RoomService roomService;


}
