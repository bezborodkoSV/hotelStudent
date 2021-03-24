package com.studenthoteltest.demo.dao.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
//shows that this is an entity that needs to be stored in the database
@Entity

@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private long numberRoom;
    @Column
    private short numberOfSeatsInTheRoom;
    @Column
    private short numberOfFreePlacesInTheRoom;
    @Column
    private String description;

//    //true
//    @OneToMany(mappedBy = "rooms")
//    @PrimaryKeyJoinColumn
//    private Set<Residents> residents;

    @OneToMany(mappedBy = "rooms")
    @PrimaryKeyJoinColumn
    private Set<Residents> residents;

    //true
    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "floor_id")
    private Floors floors;
    //true

    @OneToOne(mappedBy = "rooms")
    @PrimaryKeyJoinColumn
    private ApplicationsForAccommodation applicationsForAccommodation;

//    @OneToOne(mappedBy = "rooms")
//    @PrimaryKeyJoinColumn
//    private ApplicationsForAccommodation applicationsForAccommodation;

    //Constructor start
    public Rooms() {
    }


    //Constructor finish
    //Other

}
