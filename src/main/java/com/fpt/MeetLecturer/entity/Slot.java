package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Slot {
    @Id
    private int Id;
    @Column
    private String password;
    @Column
    private boolean status;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;



    @JsonManagedReference
    @OneToMany(mappedBy = "slot")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Booking> bookingList;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Lecturer lecturer;
    // check xem t add dung k nha -Minhdz-
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "location_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Location location;
}
