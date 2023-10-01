package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Location")
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private boolean status;





    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecturer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Lecturer lecturer;


    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Slot> slotList;

}
