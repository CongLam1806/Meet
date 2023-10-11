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
    private int id;

    private String name;

    private int semester;

    private boolean status = true;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "major_id")
    private Major major;

    @OneToMany(mappedBy = "subject")
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "subject")
    private List<Subject_Lecturer> LecturerList;
}
