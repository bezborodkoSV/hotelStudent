package com.studenthoteltest.demo.dao.repository;

import com.studenthoteltest.demo.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

List<User>findBySurname(String surname);
}
