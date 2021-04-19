package com.studenthoteltest.demo.controller.restController;


import com.studenthoteltest.demo.dao.model.Floors;
import com.studenthoteltest.demo.service.FloorsService;
import com.studenthoteltest.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/floors/")
public class FloorRestController {
    @Autowired
    private FloorsService floorsService;


//    @PathVariable("id") Long floorId
    @RequestMapping(value = "",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Floors>> getFloor(){
        List<Floors> floorsList = floorsService.allFloors();
        if (floorsList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(floorsList,HttpStatus.OK);
    }

    @RequestMapping(value = "",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Floors> saveFloor(@RequestBody Floors floors){
        HttpHeaders headers = new HttpHeaders();
        if (floors==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        if (!floorsService.saveFloor(floors)){
//            return new ResponseEntity<>(HttpStatus.);
//        }
        floorsService.saveFloor(floors);
        return new ResponseEntity<>(floors,headers,HttpStatus.CREATED);
    }

    @RequestMapping(value = "",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Floors> deleteFloor(@PathVariable("floorId") Long floorId){
        if (floorId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        floorsService.deleteFloor(floorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
