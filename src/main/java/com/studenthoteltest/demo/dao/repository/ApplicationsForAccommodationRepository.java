package com.studenthoteltest.demo.dao.repository;

import com.studenthoteltest.demo.dao.model.ApplicationsForAccommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationsForAccommodationRepository extends JpaRepository<ApplicationsForAccommodation,Long> {
    List<ApplicationsForAccommodation> findByUsers_Username(String username);
    List<ApplicationsForAccommodation> findByRooms_Id(long roomsId);
    ApplicationsForAccommodation findById(long applicationId);
}
