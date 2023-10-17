package com.fpt.MeetLecturer.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking_SlotDTO {

    private int id;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone="Asia/Ho_Chi_Minh")
    private Date meetingDate;

    private Time startTime;

    private Time endTime;
}
