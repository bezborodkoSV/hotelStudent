package com.studenthoteltest.demo.controller;


import com.studenthoteltest.demo.dao.model.Floors;
import com.studenthoteltest.demo.dao.repository.FloorsRepository;
import com.studenthoteltest.demo.service.FloorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class FloorsController {
    @Autowired
    private FloorsService floorsService;
    @Autowired
    private FloorsRepository floorsRepository;

    @GetMapping("/floors")
    public String floor(Model model){
        model.addAttribute("floorAdd",new Floors());
        model.addAttribute("allFloors", floorsService.allFloors());
        return "floors";
    }


    @PostMapping("/floors")
    public String floorAdd(@Valid @ModelAttribute("floorAdd") Floors floor,
                           Model model
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

//delete floor
    //1
    @PostMapping("/floorDelete")
    private String  deleteFloor(@RequestParam(required = true, defaultValue = "" ) Long floorId,
                                @RequestParam(required = true, defaultValue = "" ) String action,
                                Model model, RedirectAttributes redirectAttributes) {

        if (action.equals("delete")){
            if (!floorsService.checkRoomsAtFloor(floorId)){
                model.addAttribute("forAdmin","Вы не можите удалить этаж пока не разберетесь с камнатами, жильцами и заявками на заселение ");
                redirectAttributes.addFlashAttribute("forAdmin","Вы не можите удалить этаж пока не разберетесь с камнатами, жильцами и заявками на заселение ");
                return "redirect:/floors";
            }
            floorsService.deleteFloor(floorId);
        }
        return "redirect:/floors";
    }
    //

}
