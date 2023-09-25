package com.fpt.MeetLecturer.BusinessModel;

import com.fpt.MeetLecturer.EntityModel.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDTO {
    private int id;

    private String phone;

    private String note;
}
