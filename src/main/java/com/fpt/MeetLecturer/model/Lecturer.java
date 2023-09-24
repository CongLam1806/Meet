package com.fpt.MeetLecturer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

}
