package com.fpt.MeetLecturer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String note;

    private int status; //int

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slot_id")
    private Slot slot;



}
