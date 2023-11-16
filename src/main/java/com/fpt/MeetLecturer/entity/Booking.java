package com.fpt.MeetLecturer.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
    @Id
    @SequenceGenerator(
            name = "booking_sequence",
            sequenceName = "booking_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "booking_sequence"
    )
    private int id;

    private String note;

    private int status = 1; //3 loai

    private boolean toggle = true; //da bi xoa hay chua

    @ManyToOne
    @JoinColumn(name = "slotId")
    private Slot slot;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subjectId", referencedColumnName = "Id")
    private Subject subject;



    public Booking(Slot slot, Student student, Subject subject, int status) {
        this.slot = slot;
        this.student = student;
        this.subject = subject;
        this.status = 2;
    }
}
