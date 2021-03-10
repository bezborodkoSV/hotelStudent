package com.studenthoteltest.demo.dao.repository;

import com.studenthoteltest.demo.dao.model.Residents;
import com.studenthoteltest.demo.dao.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentsRepository extends JpaRepository<Residents,Long> {
    Residents findByUsers(Users users);
    Residents findByPhoneNumberOrUsers(String phoneNumber, Users users);
}
