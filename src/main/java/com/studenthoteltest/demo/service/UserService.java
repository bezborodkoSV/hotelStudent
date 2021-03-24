package com.studenthoteltest.demo.service;

import com.studenthoteltest.demo.dao.model.Residents;
import com.studenthoteltest.demo.dao.model.Role;
import com.studenthoteltest.demo.dao.model.Users;
import com.studenthoteltest.demo.dao.repository.ResidentsRepository;
import com.studenthoteltest.demo.dao.repository.RoleRepository;
import com.studenthoteltest.demo.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

//Resident method

    public List<Residents> allResidents() {
        return residentsRepository.findAll();
    }

    public List<Residents> ResidentList(Long idMin) {
        return em.createQuery("SELECT r FROM Residents r WHERE r.id > :paramId", Residents.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public boolean checkResidentsByUser(String name){
        Users user = userRepository.findByUsername(name);
        Residents residentFromDb = residentsRepository.findByUsers(user);
        if (residentFromDb!=null){
            return false;
        }
        return true;
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
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
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




}
