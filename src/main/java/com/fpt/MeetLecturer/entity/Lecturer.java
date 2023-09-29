package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table (name="Lecturer")
@Entity
public class Lecturer {
    @Id
    private int id;
    private String name;

    private String phone;

    private String note;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;
    @OneToOne(mappedBy = "lecturer")
    private User user;

    //@OneToMany(mappedBy = "lecturer")

    @JsonManagedReference
    @OneToMany(mappedBy = "lecturer")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Location> locationList;
    
    

}
