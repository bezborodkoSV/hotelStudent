package com.studenthoteltest.demo.dao.repository;

import com.studenthoteltest.demo.dao.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
    List<Users> findByResidents_FacultyContaining(String Faculty);
    List<Users> findByResidents_GroupInContaining(String groupIn);
    List<Users> findByResidents_SurnameContaining(String surname);
}
