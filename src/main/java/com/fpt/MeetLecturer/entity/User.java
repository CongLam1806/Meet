package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@Entity
public class User {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String Id;

    @Column
    private String name; //de admin dang nhap bang

    @Column
    private String email;
    @Column
    private String password;

    @Column
    private int role;

    @Value("1")
    private boolean status;

//    @JsonIgnore
//    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Lecturer lecturerList;

//    @JsonIgnore
//    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Student student;



//    @OneToMany(mappedBy = "user")
//    private List<Slot> slotList;

}
