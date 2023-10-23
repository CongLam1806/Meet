package com.fpt.MeetLecturer.business;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class DashBoardChartDTO {
    private String month;
    private long slotCount;
}
