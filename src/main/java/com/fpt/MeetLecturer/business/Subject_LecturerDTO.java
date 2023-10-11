package com.fpt.MeetLecturer.business;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject_LecturerDTO {
    @NotBlank(message = "subject should not be empty")
    private int subjectId;
    private String subjectName;
}
