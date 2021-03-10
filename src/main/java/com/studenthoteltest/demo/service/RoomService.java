package com.studenthoteltest.demo.service;

import com.studenthoteltest.demo.dao.model.Floors;
import com.studenthoteltest.demo.dao.model.Rooms;
import com.studenthoteltest.demo.dao.repository.FloorsRepository;
import com.studenthoteltest.demo.dao.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private EntityManager em;
    @Autowired
    private FloorsRepository floorsRepository;
    @Autowired
    private RoomsRepository roomsRepository;

    public List<Rooms> allRooms(){return roomsRepository.findAll();}

    public boolean saveRoom(Rooms room, short numberFloor){
        Floors floor = floorsRepository.findByNumberFloor(numberFloor);
        Rooms roomFromDb = roomsRepository.findByNumberRoom(room.getNumberRoom());
        if (roomFromDb!=null){
            return false;
        }
        room.setFloors(floor);
        roomsRepository.save(room);
        return true;
    }

    public List<Rooms> roomList(Long idMin) {
        return em.createQuery("SELECT r FROM Rooms r WHERE r.id > :paramId", Rooms.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public boolean deleteRoom(Long roomId){
        if (roomsRepository.findById(roomId).isPresent()){
            roomsRepository.deleteById(roomId);
            return true;
        }
        return false;
    }
}
