package com.fpt.MeetLecturer.business;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    private int id;

    @NotBlank
    @Size(max = 250, message = "note maximum should be 200")
    private String note;

    private Booking_SlotDTO slotInfo;

    private int status;

    private boolean toggle;

    private String subject;

    private Booking_StudentDTO studentInfo;

    private List<Slot_SubjectDTO> subjectSlot;



}
