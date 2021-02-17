package com.studenthoteltest.demo.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
    @Column
    private String group;
    @Column
    private int phoneNumber;
    @Column
    private String registration;

    //create date
//    @Column
//    private x dateCheckIn;
//    @Column
//    private x dateCheckOut;
    //create IDUsers
}
