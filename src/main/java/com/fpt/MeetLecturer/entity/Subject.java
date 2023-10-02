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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column
    private String name;
    @Column
    private int semester;
    @Column
    private boolean status;

    @ManyToMany(mappedBy = "likedSubjects", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Slot> slots;
}
