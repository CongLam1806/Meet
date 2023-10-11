package com.fpt.MeetLecturer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Account")
@Entity
public class Account {
    @Id
    @Column(unique = true)
    private String Id;

    @Column
    private String name;

    @Column
    private String email;
    @Column
    private String password;

    @Column
    private int role;

    @Value("1")
    private boolean status;

}
