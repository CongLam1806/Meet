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
public class DashBoardIndicatorLecturerDTO {
private long totalSlot;
private Time totalHours;
private long totalLocation;
private String mostDiscussSubject;
}
