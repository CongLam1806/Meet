package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Teaching")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Subject_Lecturer {

//    @Column(name = "Subject_LecturerId", nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String Subject_LecturerID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="teachingId")
    private int id;

//    @EmbeddedId
//    private SubjectLecturerKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    //@MapsId("lecturerId")
    @JoinColumn(name = "lecturerId", referencedColumnName = "Id")
    private Lecturer lecturer;

    @ManyToOne(cascade = CascadeType.ALL)
    //@MapsId("subjectId")
    @JoinColumn(name = "subjectId", referencedColumnName = "Id")
    private Subject subject;

}
