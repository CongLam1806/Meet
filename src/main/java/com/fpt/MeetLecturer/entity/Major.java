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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private int Id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Status")
    private boolean status = true;

    @OneToMany(mappedBy = "major")
    private List<Subject> subjectList;

//    @OneToOne (mappedBy = "")
//    private Student student;



}
