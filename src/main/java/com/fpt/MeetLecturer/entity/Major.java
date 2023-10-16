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

    @OneToMany(mappedBy = "major")
    private List<Subject_Major> SubjectList;


}
