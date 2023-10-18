package com.fpt.MeetLecturer.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import lombok.*;

import org.hibernate.annotations.GenericGenerator;
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
//    @GeneratedValue(generator = "sequence-generator")
//    @GenericGenerator(
//            name = "sequence-generator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "slot_sequence"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "10"),
//                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
//            }
//    )
    private int Id;

    @Column(name="password")
    private String password;

    @Column(name="startTime")
    private Time startTime;

    @Column(name="endTime")
    private Time endTime;

    @JsonFormat(pattern="dd/MM/yyyy", timezone="Asia/Ho_Chi_Minh")
    @Column(name="meetingDay")
    private Date meetingDay;

    @Column(name="mode")
    private int mode;

    @Column(name="status")
    private boolean status = true;

//    @JsonManagedReference

    @OneToMany(mappedBy = "slot")
    private List<Booking> bookingList;


    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinColumn(name = "locationId", referencedColumnName = "Id")
    private Location location;

    @OneToMany(mappedBy = "slot")
    private List<Slot_Subject> slotSubjects;

    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinColumn(name = "lecturerId", referencedColumnName = "Id")
    private Lecturer lecturer;

}
