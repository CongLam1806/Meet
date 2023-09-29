package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.Subject;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotDTO {
    private int id;
    private String password;
    private boolean status;
    private Time startTime;
    private Time endTime;
    private Date meetingDate;

    private int locationId;
    private String locationName;

    private List<SubjectResponseDTO> subjectList;


}
