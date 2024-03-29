package com.fpt.MeetLecturer.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Student")
@Entity
public class Student {
    @Id
    @Column(name="Id")
    private String Id;
    @Column
    private String code;
    @Column
    private String name;

    @JsonFormat(pattern="dd/MM/yyyy", timezone="Asia/Ho_Chi_Minh")
    @Column
    private Date dob;
    @Column
    private String address;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String curriculum;
    @Column
    private int semester;
    @Column
    private boolean status = true;


    @OneToMany(mappedBy = "student")
    private List<Booking> bookingList;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "majorId", referencedColumnName = "Id")
    private Major major;
}
