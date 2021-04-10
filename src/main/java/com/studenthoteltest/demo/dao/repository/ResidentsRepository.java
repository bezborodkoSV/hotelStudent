package com.studenthoteltest.demo.dao.repository;

import com.studenthoteltest.demo.dao.model.Residents;
import com.studenthoteltest.demo.dao.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentsRepository extends JpaRepository<Residents,Long> {
    Residents findByUsers(Users users);
    Residents findByPhoneNumber(String phoneNumber);
    List<Residents> findResidentsByRooms_Id(long roomId);
    List<Residents> findResidentsByRooms_NumberRoom(long numberRoom);
    List<Residents> findResidentsByFacultyContaining(String faculty);
    List<Residents> findResidentsByGroupInContaining(String groupIn);
    List<Residents> findResidentsBySurnameContaining(String surname);
}
