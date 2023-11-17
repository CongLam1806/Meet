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
public class SlotSubjectKey implements Serializable {
    @Column(name = "slotId")
    private int slotId;

    @Column(name = "subjectId")
    private int subjectId;
}
