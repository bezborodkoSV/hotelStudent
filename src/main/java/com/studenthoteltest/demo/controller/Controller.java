package com.studenthoteltest.demo.controller;

import com.studenthoteltest.demo.dao.model.Rooms;
import com.studenthoteltest.demo.service.ApplicationsForAccommodationService;
import com.studenthoteltest.demo.service.FloorsService;
import com.studenthoteltest.demo.service.RoomService;
import com.studenthoteltest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

//@RestController
@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private FloorsService floorsService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ApplicationsForAccommodationService applicationsForAccommodationService;
    @Autowired
    private UserService userService;

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

//

//    @RequestMapping(value = "/firstMod",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Rooms>> getFloor(){
//        List<Rooms> roomsList = roomService.allRooms();
//        if (roomsList.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(roomsList,HttpStatus.OK);
//    }

//    @RequestMapping(value = "/firstMod",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Rooms>> getFloor(){
//        List<Rooms> roomsList = roomService.roomFreeList();
//        if (roomsList.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(roomsList,HttpStatus.OK);
//    }

//
    @GetMapping("/personalArea")
    public String personalArea(Model model,Principal principal){
        System.out.println(principal.getName());
        model.addAttribute("userInfo",userService.residentList(principal.getName()));
        model.addAttribute("userApplicatonsList",applicationsForAccommodationService.personalApplicationsList(principal.getName()));
        return "personalArea";
    }
}
