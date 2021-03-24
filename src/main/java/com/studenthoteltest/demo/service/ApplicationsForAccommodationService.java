package com.studenthoteltest.demo.service;

import com.studenthoteltest.demo.dao.model.ApplicationsForAccommodation;
import com.studenthoteltest.demo.dao.model.Rooms;
import com.studenthoteltest.demo.dao.model.Users;
import com.studenthoteltest.demo.dao.repository.ApplicationsForAccommodationRepository;
import com.studenthoteltest.demo.dao.repository.RoomsRepository;
import com.studenthoteltest.demo.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationsForAccommodationService {
    @Autowired
    private ApplicationsForAccommodationRepository applicationsForAccommodationRepository;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomsRepository roomsRepository;

    public boolean save(ApplicationsForAccommodation applicationsForAccommodation,
                        String username,
                        long numberRoom){
        Rooms roomFromDb = roomsRepository.findByNumberRoom(numberRoom);
        Users userFromDb = userRepository.findByUsername(username);
        Date dateCreate = new Date();
//        List<ApplicationsForAccommodation>listApplicationsForAccommodations = applicationsForAccommodationRepository.findByUsers(userFromDb.getId());
//        if (listApplicationsForAccommodations.size()>=3){
//            return false;
//        }
        applicationsForAccommodation.setUsers(userFromDb);
        applicationsForAccommodation.setRooms(roomFromDb);
        applicationsForAccommodation.setCreationDate(dateCreate);
        applicationsForAccommodationRepository.save(applicationsForAccommodation);
        return true;
    }

    public List<ApplicationsForAccommodation> ApplicationList(Long idMin) {
        return em.createQuery("SELECT a FROM ApplicationsForAccommodation a WHERE a.id > :paramId", ApplicationsForAccommodation.class)
                .setParameter("paramId", idMin).getResultList();
    }

}
