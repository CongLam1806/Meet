package com.fpt.MeetLecturer.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
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
@Transactional(readOnly = true)
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
    @Column
    private int mode;



//    @JsonManagedReference

    @OneToMany(mappedBy = "slot")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private Collection<Booking> bookingList;


    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinColumn(name = "location_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private Location location;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "slot_subject",
            joinColumns = @JoinColumn(name = "slot_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> likedSubjects;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinColumn(name = "user_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private User user;
}
