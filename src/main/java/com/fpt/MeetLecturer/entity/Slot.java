package com.fpt.MeetLecturer.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
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
    @SequenceGenerator(name = "slotId", sequenceName = "slot_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slotId")
    @Column(name="Id")
    private int Id;

    @Column(name="password")
    private String password;

    @Column(name="startTime")
    private Time startTime;

    @Column(name="endTime")
    private Time endTime;

    @JsonFormat(pattern="dd/MM/yyyy", timezone="Asia/Ho_Chi_Minh")
    @Column(name="meetingDay")
    private LocalDate meetingDay;

    @Column(name="mode")
    private int mode;

    @NotEmpty
    @Column(name="isOnline")
    private boolean isOnline = false;

    @Column(name="status")
    private boolean status = true;

    @Column(name="toggle")
    private boolean toggle = true;

//    @JsonManagedReference

    @OneToMany(mappedBy = "slot")
    private List<Booking> bookingList;

    @ManyToOne(
            fetch= FetchType.EAGER)
    @JoinColumn(name = "locationId", referencedColumnName = "Id")
    private Location location;

    @OneToMany(mappedBy = "slot")
    private List<Slot_Subject> slotSubjects;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "lecturerId", referencedColumnName = "Id")
    private Lecturer lecturer;

}
