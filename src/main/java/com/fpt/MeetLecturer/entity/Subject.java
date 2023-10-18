package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private int Id;


    @Column(name="code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "semester")
    private int semester;

    @Column(name = "status")
    private boolean status = true;

    @OneToMany(mappedBy = "subject")
    private List<Slot_Subject> slotSubjects;

    @OneToMany(mappedBy = "subject")
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "subject")
    private List<Subject_Lecturer> LecturerList;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Subject_Major> majorList;
}
