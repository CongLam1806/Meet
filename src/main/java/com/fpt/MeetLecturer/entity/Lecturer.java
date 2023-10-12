package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

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
    @Column(name="Id")
    private int Id;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Note")
    private String note;

    @Column(name = "Email")
    private String email;

    private boolean status = true;

    @OneToMany(mappedBy = "lecturer")
    private List<Location> locationList;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
    private List<Subject_Lecturer> SubjectList;

    @OneToMany(mappedBy = "lecturer")
    private List<Slot> SlotList;

}
