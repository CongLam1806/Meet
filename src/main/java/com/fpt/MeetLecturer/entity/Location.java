package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Location")
@Entity
public class Location {
    @Id
    @SequenceGenerator(name = "locationId", sequenceName = "LocationId", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locationId")
    @Column(name="Id")
    private int id;
    private String name;
    private String address;
    private boolean status;//private == false, public == true
    private boolean toggle;//delete == false, available == true


    //@JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "lecturerId", referencedColumnName = "Id")
    //@EqualsAndHashCode.Exclude
    //@ToString.Exclude
    private Lecturer lecturer;


    //@OneToMany(mappedBy = "location") //cascade = {CascadeType.ALL},
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Slot> slotList;

}
