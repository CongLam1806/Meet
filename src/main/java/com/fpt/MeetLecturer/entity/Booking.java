package com.fpt.MeetLecturer.entity;


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
    @JoinColumn(name = "studentId", referencedColumnName = "Id")
    private Student student;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "subjectId", referencedColumnName = "Id")
//    private Subject subject;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slotId", referencedColumnName = "Id")
    private Slot slot;



}
