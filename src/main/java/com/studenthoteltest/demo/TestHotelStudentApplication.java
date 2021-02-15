package com.studenthoteltest.demo;

import com.studenthoteltest.demo.dao.model.User;
import com.studenthoteltest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class TestHotelStudentApplication {

    private final UserService userService;

    public TestHotelStudentApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestHotelStudentApplication.class, args);
    }

//    @GetMapping("/greeting")
//    public String greeting(@RequestParam(name="name",required = false,defaultValue = "World")String name, Model model){
//        model.addAttribute("name",name);
//        return "greeting";
//    }


//    @EventListener(ApplicationReadyEvent.class)
//    private void testJpaMethods(){
//        User ann = new User();
//        ann.setName("Mark");
//        ann.setLastname("Vadimirko");
//        ann.setSurname("Gangovich");
////        userService.save(ann);


//    }
}
