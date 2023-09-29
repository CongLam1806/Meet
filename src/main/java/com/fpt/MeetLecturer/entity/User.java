package com.fpt.MeetLecturer.entity;

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
    private int id;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private int role;
    @Column
    private boolean status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Lecturer lecturer;


    //    @JsonManagedReference
    @OneToMany(mappedBy = "user")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude

    private List<Booking> bookingList;
}
