package com.fpt.MeetLecturer.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String password;
    @Column
    private boolean status; //status nay co y la co chap nhan hay k
    @Column
    private Time startTime;
    @Column
    private Time endTime;
    @Column
    private Date meetingDate;



//    @JsonManagedReference
    @OneToMany(mappedBy = "slot", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private Collection<Booking> bookingList;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private Location location;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "slot_subject",
            joinColumns = @JoinColumn(name = "slot_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> likedSubjects;
}
