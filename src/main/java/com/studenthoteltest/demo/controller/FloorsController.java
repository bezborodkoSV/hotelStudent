package com.studenthoteltest.demo.controller;


import com.studenthoteltest.demo.dao.model.Floors;
import com.studenthoteltest.demo.service.FloorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class FloorsController {
    @Autowired
    private FloorsService floorsService;

    @GetMapping("/floors")
    public String floor(Model model){
        model.addAttribute("floorAdd",new Floors());
        model.addAttribute("allFloors", floorsService.allFloors());
        return "floors";
    }


    @PostMapping("/floors")
    public String floorAdd(@Valid @ModelAttribute("floorAdd") Floors floor,
                           BindingResult bindingResult,Model model
    ){
        if (!floorsService.saveFloor(floor)){
            model.addAttribute("floorError","Такой этаж уже существует");
            return "redirect:/floors";
        }
            floorsService.saveFloor(floor);
        return "redirect:/floors";
    }

    @GetMapping("/floors/gt/{floorsId}")
    private String  getFloor(@PathVariable("floorId") Long florId, Model model) {
        model.addAttribute("allFloors", floorsService.florList(florId));
        return "floors";
    }

//
//


    @GetMapping("/floorDelete")
    public String floordelete(Model model){
        model.addAttribute("allFloors", floorsService.allFloors());
        return "floorDelete";
    }

    @PostMapping("/floorDelete")
    private String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long floorId,
                               @RequestParam(required = true, defaultValue = "" ) String action) {
        if (action.equals("delete")){
            floorsService.deleteFloor(floorId);
        }
        return "redirect:/floorDelete";
    }
    @GetMapping("/floorDelete/gt/{floorsId}")
    private String  gtFloorDelete(@PathVariable("floorId") Long florId, Model model) {
        model.addAttribute("allFloors", floorsService.florList(florId));
        return "floorDelete";
    }


}
