package com.studenthoteltest.demo.dao.repository;

import com.studenthoteltest.demo.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllByName(String name);

    List<User> findAllBySurname(String surname);
}
