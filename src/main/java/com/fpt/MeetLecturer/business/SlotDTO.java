package com.fpt.MeetLecturer.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SlotDTO {
    @Id
    private int Id;

    private String password;

    @NotEmpty(message = "Start Time must not be blank")
    @JsonFormat(pattern = "HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private LocalTime startTime;

    @NotEmpty(message = "End Time must not be blank")
    @JsonFormat(pattern = "HH:mm", timezone = "Asia/Ho_Chi_Minh")
    private LocalTime endTime;

    @NotBlank(message = "Meeting Date must not be blank")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private LocalDate meetingDay;
    private int mode = 1;

    @NotEmpty
    private boolean isOnline;

    private boolean status = true;
    //    private boolean toggle = true;

    @NotEmpty(message = "Toggle must not be blank")
    private boolean toggle = true;

    private int locationId;
    private String locationName;
    private String locationAddress;

    private String lecturerId;
    private String lecturerName;

    private String linkMeet;

    private String studentName;
    private String studentEmail;
    private String studentPhone;


    private List<Slot_SubjectDTO> slotSubjectDTOS;





}
