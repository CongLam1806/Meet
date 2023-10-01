package com.fpt.MeetLecturer.business;

import lombok.*;

@Data

@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private  int id;
    private String name;
    private String address;
    private boolean status;

}
