package com.fpt.MeetLecturer.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking_SlotDTO {

    private int id;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone="Asia/Ho_Chi_Minh")
    private LocalDate meetingDate;

    @JsonFormat(pattern = "HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private LocalTime endTime;

    private String lecturerId;

    private String lecturerName;

    private String locationName;

    private String locationAddress;

    private boolean isOnline;
}
