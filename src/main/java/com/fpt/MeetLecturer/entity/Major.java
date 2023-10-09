package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Major")
@Entity
public class Major {
    @Id
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Status")
    private boolean status;

    @OneToMany(mappedBy = "major")
    private List<Subject> subjectList;

//    @OneToOne (mappedBy = "")
//    private Student student;



}
