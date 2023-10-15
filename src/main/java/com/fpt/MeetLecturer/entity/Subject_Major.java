package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Subject_Major")
@NoArgsConstructor
@Data
public class Subject_Major {

    private Long subject_majorId;

    @EmbeddedId
    private SubjectMajorKey subjectMajorKey;

    @ManyToOne
    @MapsId("majorId")
    @JoinColumn(name = "majorId")
    private Major major;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subjectId")
    private Subject subject;

    public Subject_Major(Major major, Subject subject) {
        this.subjectMajorKey = new SubjectMajorKey(major.getId(), subject.getId());
        this.major = major;
        this.subject = subject;
    }
}

