package com.fpt.MeetLecturer.business;

import com.fpt.MeetLecturer.entity.Major;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Subject name should not be blanked")
    private String name;

    private int semester;
    private boolean status;

    //nếu thay tên Major thành tên khác thì sẽ ko lấy đc info
    private MajorDTO Major;
}
