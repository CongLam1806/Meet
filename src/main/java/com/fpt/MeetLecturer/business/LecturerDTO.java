package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDTO {
    private int id;

    @NotBlank(message = "name should not blank")
    private String name;

    @NotBlank(message = "phone should not blank")
    @Pattern(regexp = "^\\d{1,11}$", message = "Invalid number !!!!")
    private String phone;


    private String note;

    private UserDTO user ;

    private List<Subject_LecturerDTO> SubjectName;

}
