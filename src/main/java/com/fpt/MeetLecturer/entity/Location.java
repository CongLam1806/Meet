package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Lecturer lecturer;

}
