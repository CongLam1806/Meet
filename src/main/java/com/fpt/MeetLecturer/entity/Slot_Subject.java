package com.fpt.MeetLecturer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;

@Entity
public class Slot_Subject {
    @Id
    private int Id;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    Slot slot;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Subject subject;
}
