package com.fpt.MeetLecturer.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import lombok.*;

import org.springframework.transaction.annotation.Transactional;


import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Slot")
@Entity
@Transactional(readOnly = true)
public class Slot {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(name="password")
    private String password;

    @Column(name="startTime")
    private Time startTime;

    @Column(name="endTime")
    private Time endTime;

    @JsonFormat(pattern="dd-MM-yyyy", timezone="Asia/Ho_Chi_Minh")
    @Column(name="meetingDay")
    private Date meetingDate;

    @Column(name="mode")
    private int mode;

    @Column(name="status")
    private boolean status;




//    @JsonManagedReference

    @OneToMany(mappedBy = "slot")
    private Collection<Booking> bookingList;


    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinColumn(name = "locationId", referencedColumnName = "Id")

    private Location location;

    @OneToMany(mappedBy = "slot")
    private List<Slot_Subject> slotSubjects;

    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinColumn(name = "lecturerId", referencedColumnName = "Id")
    private Lecturer lecturer;

}
