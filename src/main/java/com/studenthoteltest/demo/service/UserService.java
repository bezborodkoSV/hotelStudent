package com.studenthoteltest.demo.service;

import com.studenthoteltest.demo.dao.model.Residents;
import com.studenthoteltest.demo.dao.model.Role;
import com.studenthoteltest.demo.dao.model.Users;
import com.studenthoteltest.demo.dao.repository.ResidentsRepository;
import com.studenthoteltest.demo.dao.repository.RoleRepository;
import com.studenthoteltest.demo.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.AccessOptions;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Null;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ResidentsRepository residentsRepository;
    @Autowired
    private RoomService roomService;

//Resident method

    public List<Residents> allResidents() {
        return residentsRepository.findAll();
    }

    public List<Residents> residentList(String username) {
        System.out.println(username);
        return em.createQuery("SELECT r FROM Residents r WHERE r.users.username =:username", Residents.class)
                .setParameter("username", username).getResultList();
    }



    public boolean saveResident(String name,String lastname,String surname,
                                String faculty,String groupIn,String phoneNumber,
                                String registration,String username){
        Users user =userRepository.findByUsername(username);
        Residents residentFromDb = residentsRepository.findByPhoneNumber(phoneNumber);
        if(residentFromDb!=null){
            return false;
        }

        Residents resident=new Residents(name,surname,lastname,faculty,groupIn,phoneNumber,registration,user);
        residentsRepository.save(resident);
        return true;
    }
//  Resident method finish




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


    public boolean saveUser(Users user){
        Users userFromDb =userRepository.findByUsername(user.getUsername());
        if (userFromDb!=null){
            return false;
        }
        user.setRoles(Collections.singleton(new Role(5L, "ROLE_UNVERIFIED")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public Users findUserById(Long userId) {
        Optional<Users> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new Users());
    }

    public List<Users> allUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<Users> userList(Long idMin) {
        return em.createQuery("SELECT u FROM Users u WHERE u.id > :paramId", Users.class)
                .setParameter("paramId", idMin).getResultList();
    }

//methods for resident control
public List<Residents> studentList() {
    return em.createQuery("SELECT r FROM Residents r JOIN r.users.roles rl WHERE rl.name= 'ROLE_STUDENT' ", Residents.class)
            .getResultList();
}

//check-in and check-out of students
public boolean moveOutMoveTo(String action, Long residentId, Long numberRoom){
    Residents residentFromDb = residentsRepository.findById(residentId).get();
        if (action.equals("moveOut")&&numberRoom==null){
            residentFromDb.setRooms(null);
            residentsRepository.save(residentFromDb);
            return true;
        }

        if (action.equals("moveTo")){
            if (!roomService.fullRoom(numberRoom)) {
                residentFromDb.setRooms(roomService.giveRoom(numberRoom));
                residentsRepository.save(residentFromDb);
                return true;
            }
        }
        return false;
}



public boolean acceptRejectAccount(String action,Long userId){
        Users userFromDb = userRepository.findById(userId).get();
        if (userFromDb!=null){
//            Role role = roleRepository.findById(1L).get();
            if (action.equals("accept")) {
                userFromDb.getRoles().clear();
                userFromDb.getRoles().addAll(Collections.singleton(new Role(1L,"ROLE_STUDENT")));
                userRepository.save(userFromDb);
                return true;
            }
            if (action.equals("reject")) {
                userFromDb.getRoles().clear();
                userFromDb.getRoles().addAll(Collections.singleton(new Role(3L,"ROLE_GUEST")));
                userRepository.save(userFromDb);
                return true;
            }
        }
        return false;
}

    public List<Users> unverifiedUsers() {
        return em.createQuery("SELECT u FROM Users u JOIN u.roles ur WHERE ur.name ='ROLE_UNVERIFIED'", Users.class)
                .getResultList();
    }

    public List<Users> studentUsers() {
        return em.createQuery("SELECT u FROM Users u JOIN u.roles ur WHERE ur.name ='ROLE_STUDENT'", Users.class)
                .getResultList();
    }

//    public List<Residents> residentsListByNumberRoom(Long numberRoom){
//        return residentsRepository.findResidentsByRooms_NumberRoom(numberRoom);
//    }

//    OR r.rooms.floors.numberFloor >: numberF
//    .setParameter("numberF",numberFloor)

    public List<Residents> residentsListFilterByRoom(Long numberRoom) {
        return em.createQuery("SELECT r FROM Residents r WHERE r.rooms.numberRoom <:numberR", Residents.class)
                .setParameter("numberR", numberRoom).getResultList();
    }

}
