package com.fpt.MeetLecturer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SubjectMajorKey implements Serializable {
    @Column(name = "Major_Id")
    private int majorId;

    @Column(name = "Subject_Id")
    private int subjectId;

}

