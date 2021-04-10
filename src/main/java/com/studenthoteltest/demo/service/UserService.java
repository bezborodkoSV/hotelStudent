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
import java.util.*;

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

        if (action.equals("moveTo")&& roomService.presenceOfSuchRoom(numberRoom)){
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

//   Start filter lists for Jsp admin
// SOUT oll List for oll unverified Users in Bd
    public List<Users> unverifiedUsers() {
        return em.createQuery("SELECT u FROM Users u JOIN u.roles ur WHERE ur.name ='ROLE_UNVERIFIED'", Users.class)
                .getResultList();
    }
// oll Student in Bd
    public List<Users> studentUsers() {
        return em.createQuery("SELECT u FROM Users u JOIN u.roles ur WHERE ur.name ='ROLE_STUDENT'", Users.class)
                .getResultList();
    }
    // Filter by faculty
    public List<Users> studentUsersListFilterByFaculty(String faculty){
        return userRepository.findByResidents_FacultyContaining(faculty);
    }
    // Filter by group
    public List<Users> studentUsersListFilterByGroupIn(String groupIn){
        return userRepository.findByResidents_GroupInContaining(groupIn);
    }
    // Filter by surname
    public List<Users> studentUsersListFilterBySurname(String surname){
        return userRepository.findByResidents_SurnameContaining(surname);
    }
// Finish filter lists for Jsp admin



//   Start filter lists for Jsp residentControl
// Filter by number room
    public List<Residents> residentsListFilterByRoom(Long numberRoom) {
        return em.createQuery("SELECT r FROM Residents r WHERE r.rooms.numberRoom =:numberR ", Residents.class)
                .setParameter("numberR", numberRoom).getResultList();
    }
// Filter by number dloor
    public List<Residents> residentsListFilterByFloor(Short numberFloor) {
        return em.createQuery("SELECT r FROM Residents r WHERE r.rooms.floors.numberFloor =:numberFloor ", Residents.class)
                .setParameter("numberFloor", numberFloor).getResultList();
    }
// Filter by faculty
    public List<Residents> residentsListFilterByFaculty(String faculty) {
        return residentsRepository.findResidentsByFacultyContaining(faculty);
    }
// Filter by group
    public List<Residents> residentsListFilterByGroupIn(String groupIn) {
        return residentsRepository.findResidentsByGroupInContaining(groupIn);
    }
// Filter by surname
    public List<Residents> residentsListFilterBySurname(String surname) {
        return residentsRepository.findResidentsBySurnameContaining(surname);
    }
// Finish filter lists for Jsp residentControl

}
