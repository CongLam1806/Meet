package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="SlotSubject")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slot_Subject {
    @Id
    @Column(name="slotSubjectId")
    private int Id;

    @ManyToOne
    @JoinColumn(name = "slotId", referencedColumnName = "Id")
    private Slot slot;

    @ManyToOne
    @JoinColumn(name = "subjectId", referencedColumnName = "Id")
    private Subject subject;
}
