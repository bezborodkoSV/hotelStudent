package com.studenthoteltest.demo.dao.repository;

import com.studenthoteltest.demo.dao.model.Residents;
import com.studenthoteltest.demo.dao.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms,Long> {
    Rooms findByNumberRoom(long numberRoom);
    List<Rooms> findRoomsByFloors_Id(long floorId);
//    List<Residents>
}
