package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class User {
    @Id
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

    @JsonManagedReference
    @OneToOne(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Lecturer lecturerList;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Booking> bookingList;

}
