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
@Table(name = "Lecturer")
@Entity
public class Lecturer {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)

    //@Column(unique = true)
    private int id;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Note")
    private String note;

    @Column(name = "Email")
    private String email;

    @Column(name = "Status")
    private boolean status;


//    @JsonIgnore
//    @OneToOne (cascade = CascadeType.ALL)
//    @MapsId
//    @JoinColumn(name = "user_id")
//    private User user;

    @OneToMany(mappedBy = "lecturer")
    private List<Location> locationList;

    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "teach",
//            joinColumns = @JoinColumn(name = "lecturer_id"),
//            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    @OneToMany(mappedBy = "lecturer")
    private List<Subject_Lecturer> SubjectList;


}
