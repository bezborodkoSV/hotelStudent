package com.studenthoteltest.demo;

import com.studenthoteltest.demo.dao.model.User;
import com.studenthoteltest.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TestHotelStudentApplication {

    private final UserService userService;

    public TestHotelStudentApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestHotelStudentApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    private void testJpaMethods(){
        User ann = new User();
        ann.setName("Vana");
        ann.setLastname("Bybnov");
        ann.setSurname("Olegovich");
        userService.createUser(ann);


    }
}
