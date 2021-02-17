package com.studenthoteltest.demo.dao.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "applicationsForAccommodation")
public class ApplicationsForAccommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Id Users

    @Column
    private String nameSurnameLastname;
    @Column
    private String faculty;
    @Column
    private String group;
    @Column
    private int phoneNumber;
    //create column fo date
    //    @Column
    //    private x creationDate;
    //create column for IDRooms

    @Column
    private String status;
    //create column for date
//    @Column
//    private x dateOfChange;


    public ApplicationsForAccommodation() {
    }
    //constructor for oll params

    //toString
}
