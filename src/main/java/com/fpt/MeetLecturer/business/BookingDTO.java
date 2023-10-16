package com.fpt.MeetLecturer.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fpt.MeetLecturer.entity.Slot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private int id;

    private String LecturerName;

    @Size(max = 200, message = "note maximum should be 200")
    private String note;

    private Booking_SlotDTO slotDTO;

    private int status;

    private String code;


}
