package com.studenthoteltest.demo.service;

import com.studenthoteltest.demo.dao.model.Floors;
import com.studenthoteltest.demo.dao.model.Rooms;
import com.studenthoteltest.demo.dao.repository.FloorsRepository;
import com.studenthoteltest.demo.dao.repository.ResidentsRepository;
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
    @Autowired
    private ResidentsRepository residentsRepository;

    public List<Rooms> allRooms(){return roomsRepository.findAll();}

    public boolean saveRoom(Rooms room, short numberFloor){
        Floors floor = floorsRepository.findByNumberFloor(numberFloor);
        Rooms roomFromDb = roomsRepository.findByNumberRoom(room.getNumberRoom());
        if (floor==null){
            return false;
        }
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
        if (residentsRepository.findResidentsByRooms_Id(roomId).size()>0){
            return false;
        }
        if (roomsRepository.findById(roomId).isPresent()){
            roomsRepository.deleteById(roomId);
            return true;
        }
        return false;
    }

//    public boolean update(long numberRoom,short numberOfSeatsInTheRoom,
//                          short numberOfFreePlacesInTheRoom, String description,Long roomId,
//                          short numberFloor){
//        Rooms roomFromDb = roomsRepository.findRoomsById(roomId);
//            if (roomsRepository.findById(roomId).isPresent()){
//                roomFromDb.setNumberRoom(numberRoom);
//                roomFromDb.setNumberOfSeatsInTheRoom(numberOfSeatsInTheRoom);
//                roomFromDb.setNumberOfFreePlacesInTheRoom(numberOfFreePlacesInTheRoom);
//                roomFromDb.setDescription(description);
//                roomFromDb.setFloors(floorsRepository.findByNumberFloor(numberFloor));
//                roomsRepository.save(roomFromDb);
//                return true;
//            }
//        return false;
//    }

    public List<Rooms> roomFreeList() {
        return em.createQuery("SELECT r FROM Rooms r WHERE r.numberOfFreePlacesInTheRoom > 0 order by r.numberRoom", Rooms.class)
                .getResultList();
    }




}
