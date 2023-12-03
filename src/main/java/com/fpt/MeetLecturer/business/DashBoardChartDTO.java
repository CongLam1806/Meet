package com.fpt.MeetLecturer.business;

import lombok.*;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class DashBoardChartDTO {
    private String month;
    private long slotCount;
    private long meetingCount;
    private Time totalMeetingTime;
}
