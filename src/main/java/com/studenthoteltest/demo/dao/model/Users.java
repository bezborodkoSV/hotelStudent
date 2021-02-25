package com.studenthoteltest.demo.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
//shows that this is an entity that needs to be stored in the database
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String email;
    @Column
    private String password;
    @Column
    private boolean active;

    @OneToOne(mappedBy = "users")
    private Residents residents;

    @OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
    private ApplicationsForAccommodation applicationsForAccommodation;

//    @OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private ApplicationsForAccommodation applicationsForAccommodation;








//    private boolean active;

//    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;


//    @OneToOne(mappedBy = "users",cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Residents residents;
//    @OneToOne(mappedBy = "user_login",cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Residents residents;



    //Constructor start




    //Constructor finish
    //Other



}
