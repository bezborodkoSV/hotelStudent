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
import java.util.Optional;

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
                        long numberRoom) {
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



    public List<ApplicationsForAccommodation> applicationList(Long idMin) {
        return em.createQuery("SELECT a FROM ApplicationsForAccommodation a WHERE a.id > :paramId", ApplicationsForAccommodation.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public List<ApplicationsForAccommodation> allApplicationsForAccommodations() {
        return applicationsForAccommodationRepository.findAll();
    }

    public List<ApplicationsForAccommodation> unverifiedApplicationList() {
        return em.createQuery("SELECT a FROM ApplicationsForAccommodation a WHERE  a.status is null", ApplicationsForAccommodation.class)
                .getResultList();
    }

    public List<ApplicationsForAccommodation> acceptApplicationList() {
        return em.createQuery("SELECT a FROM ApplicationsForAccommodation a WHERE  a.status ='accept' ", ApplicationsForAccommodation.class)
                .getResultList();
    }

    public List<ApplicationsForAccommodation> deflectApplicationList() {
        return em.createQuery("SELECT a FROM ApplicationsForAccommodation a WHERE  a.status ='deflect' ", ApplicationsForAccommodation.class)
                .getResultList();
    }

    public boolean makingDecisionOnApplication(Long applicationId,String action){
        Date dateOfChange = new Date();
        if (applicationsForAccommodationRepository.findById(applicationId).isPresent()){
            if (action.equals("deflect")) {
                ApplicationsForAccommodation ap = applicationsForAccommodationRepository.findById(applicationId).get();
                ap.setStatus(action);
                ap.setDateOfChange(dateOfChange);
                applicationsForAccommodationRepository.save(ap);
                return true;
            }
            if (action.equals("accept")){
                ApplicationsForAccommodation ap = applicationsForAccommodationRepository.findById(applicationId).get();
                ap.setStatus(action);
                ap.setDateOfChange(dateOfChange);
                applicationsForAccommodationRepository.save(ap);
                return true;
            }
        }
        return false;
    }
}