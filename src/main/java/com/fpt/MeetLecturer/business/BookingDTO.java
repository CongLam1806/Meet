package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private int id;

    private String note;

    private int status;


}
