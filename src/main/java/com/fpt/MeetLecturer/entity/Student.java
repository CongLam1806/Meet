package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Student {
    @Id
    private int id;
    //@Column
    //private String name;
    @Column
    private String curriculum;
    @Column
    private int semester;

    //@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
//    @JsonIgnore
    @OneToOne (cascade = CascadeType.ALL, mappedBy = "student")
    @MapsId
//    @JoinColumn(name = "user_id")
    private User user;
}
