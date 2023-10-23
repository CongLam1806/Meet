package com.fpt.MeetLecturer.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DashBoardIndicatorDTO {
private long totalMeeting;
private Time totalHours;
private long totalBooking;
private String mostDiscussSubject;
}
