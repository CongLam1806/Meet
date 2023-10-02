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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    //@JoinColumn(name = "lecturer_id", referencedColumnName = "id")
    @PrimaryKeyJoinColumn
    private Lecturer lecturer;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "student_id", referencedColumnName = "id")
    @PrimaryKeyJoinColumn
    private Student student;

    //    @JsonManagedReference

    @OneToMany(mappedBy = "user")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "user")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private List<Slot> slotList;

}
