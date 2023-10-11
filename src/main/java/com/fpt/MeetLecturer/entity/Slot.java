package com.fpt.MeetLecturer.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Time;
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

    @Column
    private String password;

    @Column
    private Time startTime;

    @Column
    private Time endTime;

    @Column
    private Date meetingDate;

    @Column
    private int mode;

    @Column
    private boolean status;


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


//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "slot_subject",
//            joinColumns = @JoinColumn(name = "slot_id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id"))
//    private List<Subject> likedSubjects;

//    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    @JoinColumn(name = "lecturer_id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    private Lecturer lecturer;

//    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
//    @JoinColumn(name = "student_id")
//    private Student student;
}
