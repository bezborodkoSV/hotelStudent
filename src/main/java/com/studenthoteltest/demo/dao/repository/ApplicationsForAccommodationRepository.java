package com.studenthoteltest.demo.dao.repository;

import com.studenthoteltest.demo.dao.model.ApplicationsForAccommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationsForAccommodationRepository extends JpaRepository<ApplicationsForAccommodation,Long> {
}
