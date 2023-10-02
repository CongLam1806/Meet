package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class User {
    @Id
    @Column(unique = true)
    private int id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private int role;
    @Column
    private boolean status;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Lecturer lecturer;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Student student;

   

    @OneToMany(mappedBy = "user")

    private List<Booking> bookingList;

    @OneToMany(mappedBy = "user")
    private List<Slot> slotList;

}
