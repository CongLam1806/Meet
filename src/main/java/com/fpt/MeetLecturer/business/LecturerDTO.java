package com.fpt.MeetLecturer.business;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDTO {
    private String id;

    @NotBlank(message = "name should not blank")
    private String name;

    @NotBlank(message = "phone should not blank")
    @Pattern(regexp = "^\\d{9,11}$", message = "Invalid number !!!!")
    private String phone;

    @NotBlank(message = "email should not blank")
    @Email
    private String email;

    private String note;

    private String linkMeet;

//    private String password;

//    private List<Subject_LecturerDTO> SubjectName;

    private List<Subject_LecturerDTO> subjectList;

    private List<Lecturer_LocationDTO> locationList;
}
