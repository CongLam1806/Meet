package com.fpt.MeetLecturer.business;

import jakarta.persistence.Column;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private String id;
    private String name;
    private int semester;
    private boolean status;
}
