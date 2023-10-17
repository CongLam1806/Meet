package com.fpt.MeetLecturer.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class SlotDTO {
    @Id
    private int Id;

    @NotBlank(message = "Password must not be blank")
    private String password;

    @NotBlank(message = "Start Time must not be blank")
    private Time startTime;

    @NotBlank(message = "End Time must not be blank")
    private Time endTime;

    @NotBlank(message = "Meeting Date must not be blank")
    @JsonFormat(pattern="dd/MM/yyyy", timezone="Asia/Ho_Chi_Minh")
    private Date meetingDay;


    private int mode = 1;

    private boolean status = true;


    private int locationId;
    private String lecturerName;
    private String studentEmail;
    private List<String> subjectCode;
//    private Time currentTime;
//    private Date currentDay;
    //private SubjectResponseDTO subjectResponseDTOS;
    //private Subject subjects;



}
