package com.fpt.MeetLecturer.businessModel;

import com.fpt.MeetLecturer.entityModel.User;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerDTO {
    private int id;

    private String phone;

    private String note;

    private User user;
}
