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
  
    //@Column(unique = true)
    private int id;


    private String phone;

    private String note;

    @JsonIgnore
    @OneToOne (cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    
   
    @JsonIgnore
    @OneToMany(mappedBy = "lecturer")
    private List<Location> locationList;

    @ManyToMany
    @JoinTable(
            name = "teach",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> Subject;
    
    

}
