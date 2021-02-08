package com.studenthoteltest.demo.service;

import com.studenthoteltest.demo.dao.model.User;
import com.studenthoteltest.demo.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> findAllByName(String name){
        return userRepository.findAllByName(name);
    }

    public List<User> findWhereEmailIsGmail(String surname){
        return userRepository.findAllBySurname(surname);
    }



}
