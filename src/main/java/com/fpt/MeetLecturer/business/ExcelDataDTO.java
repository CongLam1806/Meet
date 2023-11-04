package com.fpt.MeetLecturer.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDataDTO {
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private LocalDate meetingDay;
    private LocalTime startTime;
    private LocalTime endTime;
    private String locationId;
    private String subjects;
    private int mode;
    private String studentEmail;
    private String password;
    //private String lecturerId;

}
