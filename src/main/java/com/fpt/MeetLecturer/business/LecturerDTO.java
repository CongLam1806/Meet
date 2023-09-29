package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDTO {
    private int id;

    private String phone;

    private String note;

    private int Userid;

}
