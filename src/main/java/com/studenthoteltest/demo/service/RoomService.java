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

    public Rooms giveRoom(long numberRoom){
        Rooms roomFromDb = roomsRepository.findByNumberRoom(numberRoom);
        return roomFromDb;
    }
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

//    give us list where room have free places
    public List<Rooms> roomFreeList() {
        return em.createQuery("SELECT r FROM Rooms r WHERE r.numberOfFreePlacesInTheRoom > 0 order by r.numberRoom", Rooms.class)
                .getResultList();
    }

    //    give us list where room have free places
    public List<Rooms> roomFoolPlaceList() {
        return em.createQuery("SELECT r FROM Rooms r WHERE r.numberOfFreePlacesInTheRoom > r.numberOfSeatsInTheRoom order by r.numberRoom", Rooms.class)
                .getResultList();
    }


//    automatic check of the number of free places in the room
public boolean checkTheNumberOfFreeSeats(){
//        take all rooms
        List<Rooms> roomsList = roomsRepository.findAll();
//        foreach
    for (Rooms o:roomsList) {
//        take room from Db by Id(take from roomslist, object give us id this object)
        Rooms room = roomsRepository.findById(o.getId()).get();
//        put number of free place in room(all places in the room - size of the list people in this room)
        room.setNumberOfFreePlacesInTheRoom((short) (o.getNumberOfSeatsInTheRoom()-residentsRepository.findResidentsByRooms_Id(o.getId()).size()));
        System.out.println(o.getId()+" "+o.getNumberOfSeatsInTheRoom()+" "+residentsRepository.findResidentsByRooms_Id(o.getId()).size()+" "+o.getNumberOfFreePlacesInTheRoom());
//        save change
        roomsRepository.save(room);
    }
        return true;
}

public boolean presenceOfSuchRoom(long numberRoom){
        if (roomsRepository.findByNumberRoom(numberRoom) == null){
            return false;
        }
        return true;
}
public boolean fullRoom(long numberRoom){
        int numb = roomsRepository.findByNumberRoom(numberRoom).getNumberOfFreePlacesInTheRoom();
    System.out.println("dsfcvxasdc   "+numb);
    if (roomsRepository.findByNumberRoom(numberRoom).getNumberOfFreePlacesInTheRoom()==0){
        return  true;
    }
        return false;
}

}
