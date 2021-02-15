package com.studenthoteltest.demo.service;

import com.studenthoteltest.demo.dao.model.User;
//import com.studenthoteltest.demo.dao.repository.RoleRepository;
import com.studenthoteltest.demo.dao.repository.UserRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    public List<User> findAll() {
//
//        var users = (List<User>) userRepository.findAll();
//
//        return users;
//    }

}
