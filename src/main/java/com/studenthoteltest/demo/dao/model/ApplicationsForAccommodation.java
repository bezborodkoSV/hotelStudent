package com.studenthoteltest.demo.dao.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
//shows that this is an entity that needs to be stored in the database
@Entity

@Table(name = "applications_for_accommodation")
public class ApplicationsForAccommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "nameSurnameLastname")
    private String nameSurnameLastname;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "groupIn")
    private String groupIn;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    //create column fo date
//    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date creationDate;

    @Column(name = "status")
    private String status;
    //create column for date
    @Column
    private Date dateOfChange;

    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToOne
    @JoinColumn(name = "rooms_id")
    private Rooms rooms;

//    @OneToOne(cascade = CascadeType.ALL)
//    @MapsId
//    @JoinColumn(name = "users_id")
//    private Users user;
















//    @OneToOne(cascade = CascadeType.ALL)
//    @MapsId
//    @JoinColumn(name = "user_login_id")
//    private Users user;
//    //true
//    @OneToOne(cascade = CascadeType.ALL)
//    @MapsId
//    @JoinColumn(name="rooms_id")
//    private Rooms rooms;

    //Constructor start





//Constructor finish
    //Other


}
