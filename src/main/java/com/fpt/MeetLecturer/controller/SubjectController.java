package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.service.BookingService;
import com.fpt.MeetLecturer.service.SubjectService;
import jakarta.validation.Valid;
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
    public void createNewSubject(@Valid @RequestBody SubjectDTO subjectDTO){
         subjectService.createSubject(subjectDTO);
    }

    @PutMapping("/{id}")
    public void updateSubject(@Valid @RequestBody SubjectDTO subjectDTO, @PathVariable String id){
        subjectService.updateSubject(subjectDTO,id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@Valid @PathVariable String id){
        subjectService.deleteSubject(id);
    }
}