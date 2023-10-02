package com.fpt.MeetLecturer.business;

import org.springframework.http.HttpStatus;

public class ResponseDTO {
    private HttpStatus status;
    private String message;
    private Object data;
}
