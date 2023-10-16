package com.fpt.MeetLecturer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SubjectLecturerKey implements Serializable {
    @Column(name = "Lecturer_Id")
    private String lecturerId;

    @Column(name = "Subject_Id")
    private int subjectId;



}

