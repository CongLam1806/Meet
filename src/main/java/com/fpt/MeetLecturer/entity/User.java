package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

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


    private String name;


    private String password;


    private String email;


    private int role;

    @Value("1")
    private boolean status;

//    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturerList;

//    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "lecturer_id")
    private Student student;


    @OneToMany(mappedBy = "user")
    private List<Booking> bookingList;

    @OneToMany(mappedBy = "user")
    private List<Slot> slotList;

}
