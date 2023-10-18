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
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(unique = true)
    private String Id;

    private String name;

    private String phone;

    private String note;

    private String email;

    private String password;

    private boolean status = true;

    //để map entity sang dto thì tên biến của entity và dto trùng nhau
    @OneToMany(mappedBy = "lecturer")
    private List<Location> locationList;

    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
    private List<Subject_Lecturer> SubjectList;

    @OneToMany(mappedBy = "lecturer")
    private List<Slot> SlotList;

}
