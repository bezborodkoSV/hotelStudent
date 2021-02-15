package com.studenthoteltest.demo;
import com.studenthoteltest.demo.dao.model.User;
import com.studenthoteltest.demo.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private UserRepository userRepository;



    //Вывод содержимого БД
    @GetMapping
    public String main(Map<String,Object> model){
        Iterable<User> users = userRepository.findAll();

        model.put("users",users);
        return "main";
    }

    // Добавление в БД из post значения
    @PostMapping
    public String add(@RequestParam String name,@RequestParam String surname, @RequestParam String lastname,Map<String,Object> model){
        User user = new User(name, surname, lastname);
        userRepository.save(user);
        //Временная проверка на работоспособность для метода добавления(В дальнейшем будет переделано)
        Iterable<User> users = userRepository.findAll();

        model.put("users",users);
        //Конец бредовой проверки

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String,Object>model){
        Iterable<User> users;
        // Проверяем если surname была задан или не был пустым то ищем
        if (filter!=null && filter.isEmpty()) {
            users = userRepository.findBySurname(filter);
            model.put("users", users);
        }
        //в противном случае выводим все
        else {
            users= userRepository.findAll();
        }
//Метод findAll возвращает Iterable, а метод findBySurname возвращает List. Соответсвенно List имплементирует Iterable и используем как общий.

        return "main";
    }
}
