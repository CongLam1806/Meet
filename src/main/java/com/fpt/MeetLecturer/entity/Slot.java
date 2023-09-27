package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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


    @JsonManagedReference
    @OneToMany(mappedBy = "slot")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Booking> bookingList;
}
