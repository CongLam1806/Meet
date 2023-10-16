package com.fpt.MeetLecturer.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;


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

    @JsonFormat(pattern="dd-MM-yyyy", timezone="Asia/Ho_Chi_Minh")
    private Date meetingDay;

    @Value("1")
    private int mode;

    @Value("1")
    private boolean status;


    private int locationId;
    private String lecturerName;
    private String studentEmail;
    private List<String> subjectCode;
    //private SubjectResponseDTO subjectResponseDTOS;
    //private Subject subjects;



}
