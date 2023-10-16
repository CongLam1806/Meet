package com.fpt.MeetLecturer.business;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String Id;
    private String code;

    private String name;

    private Date dob;

    private String address;

    @JsonFormat(pattern="dd-MM-yyyy", timezone="Asia/Ho_Chi_Minh")
    private String phone;

    private String email;

    private String curriculum;

    private int semester;

    private boolean status;


}
