package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.service.BookingService;
import com.fpt.MeetLecturer.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    @GetMapping("")
    public List<SubjectDTO> getAllSubject() {
        return subjectService.getAllSubject();
    }

    @GetMapping("/status")
    public List<SubjectDTO> getAvailableSubject() {
        return subjectService.getAvailableSubject();
    }

    @PostMapping("")
    public void createNewSubject(@RequestBody SubjectDTO subjectDTO){
         subjectService.createSubject(subjectDTO);
    }

    @PutMapping("")
    public void updateSubject(@RequestBody SubjectDTO subjectDTO){
        subjectService.updateSubject(subjectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable int id){
        subjectService.deleteBooking(id);
    }
}
