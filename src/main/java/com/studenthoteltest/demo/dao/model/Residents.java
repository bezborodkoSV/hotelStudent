package com.studenthoteltest.demo.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
//shows that this is an entity that needs to be stored in the database
@Entity

@Table(name = "residents")
public class Residents {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String lastname;
    @Column
    private String faculty;
    @Column(name = "groupIn")
    private String groupIn;
    @Column(unique = true)
    private String phoneNumber;
    @Column
    private String registration;
    @Column
    private Date dateCheckIn;
    @Column
    private Date dateCheckOut;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "users_id")
    private Users users;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "rooms_id")
private Rooms rooms;

    public Residents() {
    }

    public Residents(long id, String name, @Size(min = 3, message = "") String surname, String lastname, @Size(min = 3, message = "") String faculty, @Size(min = 10, message = "") String groupIn, @Size(min = 10, max = 10) String phoneNumber, String registration, Users users) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.lastname = lastname;
        this.faculty = faculty;
        this.groupIn = groupIn;
        this.phoneNumber = phoneNumber;
        this.registration = registration;
        this.users = users;
    }
    ////true
//    @OneToOne(cascade = CascadeType.ALL)
//    @MapsId
//    @JoinColumn(name = "users_id")
//    private Users users;
//
////true
//    @ManyToOne(cascade = CascadeType.ALL)
//    @MapsId
//    @JoinColumn(name="rooms_id")
//    private Rooms rooms;

    //create date
//    @Column
//    private x dateCheckIn;
//    @Column
//    private x dateCheckOut;

    //Constructor start



    //Constructor finish
    //Other

}
