package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@Data
public class Subject_Lecturer {

//    @Column(name = "Subject_LecturerId", nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String Subject_LecturerID;


    @EmbeddedId
    private SubjectLecturerKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("lecturerId")
    @JoinColumn(name = "Lecturer_Id")
    private Lecturer lecturer;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("subjectId")
    @JoinColumn(name = "Subject_Id")
    private Subject subject;

    public Subject_Lecturer(Lecturer lecturer, Subject subject) {
        this.id = new SubjectLecturerKey(lecturer.getId(), subject.getId());
        this.lecturer = lecturer;
        this.subject = subject;
    }
}
