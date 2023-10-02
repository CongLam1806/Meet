package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.MajorDTO;
import com.fpt.MeetLecturer.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/major")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping("")
    public List<MajorDTO> getAllMajor(){
        return majorService.getAllMajor();
    }
}
