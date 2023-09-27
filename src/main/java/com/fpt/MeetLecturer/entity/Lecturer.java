package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name="Lecturer")
@Entity
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String phone;

    private String note;


    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @JsonManagedReference
    @OneToMany(mappedBy = "lecturer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Slot> slotList;
    // check xem t add dung k nha -Minhdz-
    @JsonBackReference
    @OneToMany(mappedBy = "location")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Location> location;

}
