package com.studenthoteltest.demo.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "student")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String lastname;

//    public User() {
//    }
//
//    public User(long id, String name, String surname, String lastname) {
//        this.id = id;
//        this.name = name;
//        this.surname = surname;
//        this.lastname = lastname;
//    }
}
