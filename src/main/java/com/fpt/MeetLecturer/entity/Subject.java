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
    private int Id;

    @Column(name="")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="semester")
    private int semester;

    @Column(name="status")
    private boolean status = true;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "majorId", referencedColumnName = "Id")
    private Major major;

    @OneToMany(mappedBy = "subject")
    private List<Slot_Subject> slotSubjects;

//    @OneToMany(mappedBy = "subject")
//    private List<Booking> bookingList;

    @OneToMany(mappedBy = "subject")
    private List<Subject_Lecturer> LecturerList;
}
