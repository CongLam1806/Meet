package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name="Lecturer")
@Entity
public class Lecturer {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String phone;

    private String note;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;
    //@OneToOne(mappedBy = "lecturer", cascade = CascadeType.ALL)
    @JsonIgnore
    @OneToOne (cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    //@JoinColumn(name = "user_id", referencedColumnName = "id")


    //@OneToMany(mappedBy = "lecturer")

    @JsonIgnore
    @OneToMany(mappedBy = "lecturer")
    private List<Location> locationList;
    
    

}
