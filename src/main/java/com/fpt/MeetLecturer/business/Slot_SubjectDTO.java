package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Slot_Subject;
import com.fpt.MeetLecturer.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Slot_SubjectDTO {
    private int id;
    private Slot slot;
    private Subject subject;

}
