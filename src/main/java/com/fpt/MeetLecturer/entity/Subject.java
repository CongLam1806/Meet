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

    @GeneratedValue(strategy = GenerationType.AUTO)

    private String id;
//    private int id;

    private String name;

    private int semester;

    private boolean status;

//    @ManyToMany(mappedBy = "likedSubjects", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Slot> slots;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "major_id")
    private Major major;

//    @ManyToMany(mappedBy = "likedSubjects")
//    private List<Slot> slotList;

    //    @ManyToMany(mappedBy = "Subject")
    @OneToMany(mappedBy = "subject")
    private List<Subject_Lecturer> LecturerList;
}
