package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @GetMapping("{email}")
    public LecturerDTO getLecturerByEmail(@PathVariable String email){
        return lecturerService.getLecturerByEmail(email);
    }

    @GetMapping("")
    public List<LecturerDTO> getAllLecturer(){
        return lecturerService.getAllLecturer();
    }

    @PutMapping("/{id}")
    public void updateLecturer(@RequestBody LecturerDTO newLecturer, @PathVariable int id ){
         lecturerService.updateLecturer(newLecturer, id);
    }

}
