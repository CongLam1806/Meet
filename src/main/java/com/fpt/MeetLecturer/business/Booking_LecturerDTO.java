package com.fpt.MeetLecturer.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking_LecturerDTO {
    private String lecturerEmail;
    private String lecturerPhone;
}
