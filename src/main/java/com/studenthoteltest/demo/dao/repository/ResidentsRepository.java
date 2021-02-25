package com.studenthoteltest.demo.dao.repository;

import com.studenthoteltest.demo.dao.model.Residents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentsRepository extends JpaRepository<Residents,Long> {
}
