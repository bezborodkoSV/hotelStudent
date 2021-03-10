package com.studenthoteltest.demo.service;

import com.studenthoteltest.demo.dao.model.Floors;
import com.studenthoteltest.demo.dao.repository.FloorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class FloorsService {

    @Autowired
    private FloorsRepository floorsRepository;

    @PersistenceContext
    private EntityManager em;


    public boolean saveFloor(Floors floor){
        Floors floorFromDb = floorsRepository.findByNumberFloor(floor.getNumberFloor());
        if (floorFromDb!=null){
            return false;
        }
        floorsRepository.save(floor);
        return true;
    }

    public List<Floors> allFloors(){return floorsRepository.findAll();}

    public boolean deleteFloor(Long floorId){

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


//    public Floors   findFloorsById(Long floorId) {
//        Optional<Floors> floorFromDb = floorsRepository.findById(floorId);
//        return floorFromDb.orElse(new Floors());
//    }
}
