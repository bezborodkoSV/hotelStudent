package com.studenthoteltest.demo.controller;

import com.studenthoteltest.demo.dao.model.Rooms;
import com.studenthoteltest.demo.dao.repository.FloorsRepository;
import com.studenthoteltest.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private FloorsRepository floorsRepository;

    @GetMapping("/room")
    public String room(Model model){
        model.addAttribute("roomAdd",new Rooms());
        model.addAttribute("allRoom",roomService.allRooms());
        return "room";
    }


    @PostMapping("/addRoom")
        public String addRoom(@ModelAttribute("roomAdd") Rooms room,
                              @RequestParam short numberFloor,
                              Model model){
        if (!roomService.saveRoom(room,numberFloor)){
            if (floorsRepository.findByNumberFloor(numberFloor)==null){
                model.addAttribute("floorError","Такого этажа нет");
                return "room";
            }
            model.addAttribute("roomError","Такая комната уже существует");
            return "room";
        }
        roomService.saveRoom(room,numberFloor);
        return "redirect:/room";
    }

    @PostMapping("/room")
    private String  deleteRoom(@RequestParam(required = true, defaultValue = "" ) Long roomId,
                               @RequestParam(required = true, defaultValue = "" ) String action,
                               RedirectAttributes redirectAttributes) {
        if (action.equals("delete")){
            if (!roomService.deleteRoom(roomId)){
                redirectAttributes.addFlashAttribute("forAdminMessage","Щоб видалити кімнату, спершу виселіть і проінформуйте мешканців");
                return "redirect:/room";
            }
            roomService.deleteRoom(roomId);
        }
        return "redirect:/room";
    }

//    @PostMapping("roomUpdate")
//        private String updateRoom(@RequestParam short numberFloor, @RequestParam long numberRoom,
//                              @RequestParam short numberOfSeatsInTheRoom,@RequestParam short numberOfFreePlacesInTheRoom,
//                              @RequestParam String description,
//                              @RequestParam(required = true, defaultValue = "" ) Long roomId,
//                              @RequestParam(required = true, defaultValue = "" ) String action){
//       if (action.equals("update")){
//            roomService.update(numberRoom,numberOfSeatsInTheRoom,numberOfFreePlacesInTheRoom,description,roomId,numberFloor);
//       }
//        return "redirect:/room";
//    }

//    Delete
    @GetMapping("/room/gt/{roomsId}")
    private String  gtFloor(@PathVariable("roomId") Long roomId, Model model) {
        model.addAttribute("allRoom", roomService.roomList(roomId));
        return "room";
    }

}
