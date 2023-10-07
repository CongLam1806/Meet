package com.fpt.MeetLecturer.business;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {
    private  int id;

    @NotBlank(message = "Address label cannot be blank!")
    @Size(min=3, message = "Address label should be at least 3 characters")
    @Size(max=30, message = "Address label should not be greater than 30 characters")
    private String name;

    @NotBlank(message = "Address cannot be blank!")
    @Size(min=5, message = "Address should be at least 5 characters")
    @Size(max=100, message = "Address should not be greater than 100 characters")
    private String address;

    private boolean status;

}
