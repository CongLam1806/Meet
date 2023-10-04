package com.fpt.MeetLecturer.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
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

    @NotBlank(message = "Password must not be blank")
    private String password;

    @Value("1")
    private boolean status;

    @NotBlank(message = "Start Time must not be blank")
    private Time startTime;

    @NotBlank(message = "End Time must not be blank")
    private Time endTime;

    @NotBlank(message = "Meeting Date must not be blank")
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date meetingDate;

    @Value("1")
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
