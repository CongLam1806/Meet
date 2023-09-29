package com.fpt.MeetLecturer.business;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private String id;
    private String name;
    private int semester;
    private boolean status;
}
