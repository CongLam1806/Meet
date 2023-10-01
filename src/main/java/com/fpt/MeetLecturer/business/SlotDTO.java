package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.Subject;
import jakarta.persistence.Column;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
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
