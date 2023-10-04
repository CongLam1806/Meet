package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Subject {
    @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
//    private String id;
    private int id;
    @Column
    private String name;
    @Column
    private int semester;
    @Column
    private boolean status;

    @ManyToMany(mappedBy = "likedSubjects", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Slot> slots;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "major_id")
    private Major major;

    @ManyToMany(mappedBy = "likedSubjects")
    private List<Slot> slotList;

    @ManyToMany(mappedBy = "Subject")
    private List<Lecturer> lecturerList;
}
