package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Student")
@Entity
public class Student {
    @Id
    @Column
    private String Id;
    @Column
    private String code;
    @Column
    private String name;
    @Column
    private Date dob;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String curriculum;
    @Column
    private int semester;
    @Column
    private boolean status;

    //@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
//    @JsonIgnore
//    @OneToOne (cascade = CascadeType.ALL)
//    @MapsId
//    @JoinColumn(name = "user_id")
//    private User user;

//    @OneToMany(mappedBy = "student")
//    private List<Slot> slotList;
    @OneToMany(mappedBy = "student")
    private List<Booking> bookingList;
}
