package com.studenthoteltest.demo.dao.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
//shows that this is an entity that needs to be stored in the database
@Entity

@Table(name = "floors")
public class Floors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private short numberFloor;
    @Column
    private int numberOfRoomsPerFloor;
    @Column
    private int numberOfFreeRoomsOnTheFloor;

    //true
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "floors")
    private Set<Rooms> rooms;

    //Constructor start

    public Floors() {
    }

    public Floors(short numberFloor, int numberOfRoomsPerFloor, int numberOfFreeRoomsOnTheFloor, Set<com.studenthoteltest.demo.dao.model.Rooms> rooms) {
        this.numberFloor = numberFloor;
        this.numberOfRoomsPerFloor = numberOfRoomsPerFloor;
        this.numberOfFreeRoomsOnTheFloor = numberOfFreeRoomsOnTheFloor;
        this.rooms = rooms;
    }
    //Constructor finish
    //Other

    @Override
    public String toString() {
        return "Floors{" +
                "id=" + id +
                ", numberFloor=" + numberFloor +
                ", numberOfRoomsPerFloor=" + numberOfRoomsPerFloor +
                ", numberOfFreeRoomsOnTheFloor=" + numberOfFreeRoomsOnTheFloor +
                ", rooms=" + rooms +
                '}';
    }
}
