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

    private int status = 1; //int

    private boolean toggle = true;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slotId")
    private Slot slot;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subjectId", referencedColumnName = "Id")
    private Subject subject;






}
