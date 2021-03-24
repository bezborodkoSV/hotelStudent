package com.studenthoteltest.demo.service;

import com.studenthoteltest.demo.dao.model.Floors;
import com.studenthoteltest.demo.dao.model.Rooms;
import com.studenthoteltest.demo.dao.repository.FloorsRepository;
import com.studenthoteltest.demo.dao.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class FloorsService {

    @Autowired
    private FloorsRepository floorsRepository;
    @Autowired
    private RoomsRepository roomsRepository;

    @PersistenceContext
    private EntityManager em;

    public boolean checkRoomsAtFloor(long floorId ){
        List<Rooms> rooms = roomsRepository.findRoomsByFloors_Id(floorId);
        if(rooms.size()>0){
            return false;
        }
        return true;
    }

    public boolean saveFloor(Floors floor){
        Floors floorFromDb = floorsRepository.findByNumberFloor(floor.getNumberFloor());
        if (floorFromDb!=null){
            return false;
        }
        floorsRepository.save(floor);
        return true;
    }

    public List<Floors> allFloors(){return floorsRepository.findAll();}

    public boolean deleteFloor(Long floorId) {
        if (floorsRepository.findById(floorId).isPresent()){
            floorsRepository.deleteById(floorId);
            return true;
        }
        return false;
    }

    public List<Floors> florList(Long idMin) {
        return em.createQuery("SELECT f FROM Floors f WHERE f.id > :paramId", Floors.class)
                .setParameter("paramId", idMin).getResultList();
    }




}
