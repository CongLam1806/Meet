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
@Table(name = "Location")
@Entity
public class Location {
    @Id
    private int id;
    private String name;
    private String address;
    private boolean status;
    private int lecturerId; //xoa dong nay

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Lecturer lecturer;

}
