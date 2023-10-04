package com.fpt.MeetLecturer.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fpt.MeetLecturer.entity.Subject;
import jakarta.persistence.Column;
import lombok.*;
import org.joda.time.LocalDate;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SlotDTO {
    private int id;
    private String password;
    private boolean status;
    private Time startTime;
    private Time endTime;
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date meetingDate;
    private int mode;

    private int locationId;
    private String lecturerName;

    private List<SubjectResponseDTO> subjectList;



}
