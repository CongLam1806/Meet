package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Major {
    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "major")
    private List<Subject> subjectList;

//    @OneToOne (mappedBy = "")
//    private Student student;



}
