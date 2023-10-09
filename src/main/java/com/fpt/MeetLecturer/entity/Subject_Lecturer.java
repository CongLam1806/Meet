package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Subject_Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Lecturer_Id")
    private Lecturer lecturer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Subject_Id")
    private Subject subject;
}
