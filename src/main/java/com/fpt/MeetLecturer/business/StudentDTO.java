package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private int id;
    private String curriculum;
    private int semester;

    private User user;
}
