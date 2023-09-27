package com.fpt.MeetLecturer.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Booking {
    @Id
    private int id;

    private String note;

    private boolean status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "slot_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Slot slot;


}
