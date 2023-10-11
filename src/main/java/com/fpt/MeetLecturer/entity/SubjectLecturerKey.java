package com.fpt.MeetLecturer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SubjectLecturerKey implements Serializable {
    @Column(name = "Lecturer_Id")
    private Integer lecturerId;

    @Column(name = "Subject_Id")
    private Integer subjectId;

}
