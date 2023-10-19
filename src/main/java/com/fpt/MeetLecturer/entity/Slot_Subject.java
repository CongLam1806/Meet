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
    @SequenceGenerator(
            name = "slot_subject_sequence",
            sequenceName = "slot_subject_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "slot_subject_sequence"
    )
    @Column(name="slotSubjectId")
    private int Id;

    @ManyToOne
    @JoinColumn(name = "slotId", referencedColumnName = "Id")
    private Slot slot;

    @ManyToOne
    @JoinColumn(name = "subjectId", referencedColumnName = "Id")
    private Subject subject;

    public Slot_Subject(Slot slot, Subject subject) {
        this.slot = slot;
        this.subject = subject;
    }
}
