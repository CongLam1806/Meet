package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.BusinessModel.LecturerDTO;
import com.fpt.MeetLecturer.EntityModel.Lecturer;
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
    public List<LecturerDTO> getLecturerByEmail(@PathVariable String email){
        return lecturerService.getAllLecturer();
    }

    @GetMapping("")
    public List<LecturerDTO> getAllLecturer(){
        return lecturerService.getAllLecturer();
    }

    @PutMapping("/{id}")
    public LecturerDTO updateLecturer(@RequestBody LecturerDTO newLecturer, @PathVariable int id ){
        return lecturerService.updateLecturer(newLecturer, id);
    }

}
