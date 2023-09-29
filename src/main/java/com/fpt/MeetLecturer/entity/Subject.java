package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Subject {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private int semester;
    @Column
    private boolean status;

    @ManyToMany(mappedBy = "likedSubjects")
    private List<Slot> slots;
}
