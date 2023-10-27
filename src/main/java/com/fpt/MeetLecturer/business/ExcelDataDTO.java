package com.fpt.MeetLecturer.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDataDTO {
    private LocalDate meetingDay;
    private Time startTime;
    private Time endTime;
    private int locationId;
    private String subjects;
    private int mode;
    private String studentEmail;
    private String password;
    //private String lecturerId;

}
