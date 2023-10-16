package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Teaching")
@NoArgsConstructor
@Data
public class Subject_Lecturer {

//    @Column(name = "Subject_LecturerId", nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String Subject_LecturerID;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private int id;

    private long teachId;

    @EmbeddedId
    private SubjectLecturerKey subjectLecturerKey;

    @ManyToOne
    @MapsId("lecturerId")
    @JoinColumn(name = "lecturerId", referencedColumnName = "Id")
    private Lecturer lecturer;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subjectId", referencedColumnName = "Id")
    private Subject subject;

    public Subject_Lecturer(Lecturer lecturer, Subject subject) {
        this.subjectLecturerKey = new SubjectLecturerKey(lecturer.getId(), subject.getId());
        this.lecturer = lecturer;
        this.subject = subject;
    }
}
