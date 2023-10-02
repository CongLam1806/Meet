package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private int id;
    private String name;
    private String password;
    private String email;
    private int role;

    private Lecturer lecturer;
    private Student student;
}
