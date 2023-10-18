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
    @SequenceGenerator(
            name = "major_sequence",
            sequenceName = "major_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "major_sequence"
    )
    @Column(name="Id")
    private int Id;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "major")
    private List<Subject_Major> SubjectList;


}
