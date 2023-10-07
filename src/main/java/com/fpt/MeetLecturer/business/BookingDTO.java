package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.User;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private int id;

    @Size(max = 200, message = "Max should be 200")
    private String note;


    private int status;


}
