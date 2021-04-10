package com.studenthoteltest.demo.controller.restController;


import com.studenthoteltest.demo.dao.model.Floors;
import com.studenthoteltest.demo.dao.model.Rooms;
import com.studenthoteltest.demo.service.FloorsService;
import com.studenthoteltest.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController("/api/forOll/")
public class ForOllRestController {
//    @Autowired
//    private RoomService roomService;

//    @RequestMapping(value = "",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<List<Rooms>> getFloor(){
//        List<Rooms> roomsList = roomService.roomFreeList();
//        if (roomsList.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(roomsList,HttpStatus.OK);
//    }
}
