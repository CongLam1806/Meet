package com.fpt.MeetLecturer.business;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data

@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private  int id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    private boolean status;

}
