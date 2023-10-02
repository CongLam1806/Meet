package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.Major;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    //    private String id;

    private int id;
    private String name;
    private int semester;
    private boolean status;

    //nếu thay tên Major thành tên khác thì sẽ ko lấy đc info
    private MajorDTO Major;
}
