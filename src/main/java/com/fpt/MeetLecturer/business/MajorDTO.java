package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.Subject;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorDTO {
    private int id;

    @NotBlank(message = "Major's name should be included")
    private String name;

    private boolean status;
//    private List<SubjectDTO> subject;
}
