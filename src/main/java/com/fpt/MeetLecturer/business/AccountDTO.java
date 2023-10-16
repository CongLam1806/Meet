package com.fpt.MeetLecturer.business;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String Id;

    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotBlank(message = "Email must not be blank")
    private String email;

    private String password;

    @NotBlank(message = "Role must no be blank")
    private int role;

    private boolean status;



}
