package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Slot {
    @Id
    private int id;
    @Column
    private String password;
    @Column
    private boolean status; //status nay co y la co chap nhan hay k
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;
    @Column
    private Date meetingDate;



//    @JsonManagedReference
    @OneToMany(mappedBy = "slot")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private Collection<Booking> bookingList;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private Lecturer lecturer;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "location_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private Location location;
}
