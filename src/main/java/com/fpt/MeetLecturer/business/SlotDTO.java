package com.fpt.MeetLecturer.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.entity.Slot_Subject;
import com.fpt.MeetLecturer.entity.Subject;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Time;
import java.text.DateFormat;
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

    @NotBlank(message = "Password must not be blank")
    private String password;

    @NotBlank(message = "Start Time must not be blank")
    private Time startTime;

    @NotBlank(message = "End Time must not be blank")
    private Time endTime;

    @NotBlank(message = "Meeting Date must not be blank")

    private Date meetingDate;

    @Value("1")
    private int mode;

    @Value("1")
    private boolean status;


    private int locationId;
    private String lecturerName;
    private String studentEmail;
    private String subjectName;
    //private List<Slot_Subject> slot_subject;



}
