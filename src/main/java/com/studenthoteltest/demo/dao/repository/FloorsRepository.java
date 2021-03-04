package com.studenthoteltest.demo.dao.repository;

import com.studenthoteltest.demo.dao.model.Floors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloorsRepository extends JpaRepository<Floors,Long> {
}
