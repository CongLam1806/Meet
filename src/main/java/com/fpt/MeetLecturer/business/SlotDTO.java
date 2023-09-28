package com.fpt.MeetLecturer.business;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlotDTO {
    private int Id;
    private String password;
    private boolean status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
