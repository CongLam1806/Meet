package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.service.BookingService;
import com.fpt.MeetLecturer.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{code}")
    public SubjectDTO getSubjectByCode(@PathVariable String code) {
        return subjectService.getSubjectByCode(code);
    }

    @GetMapping("/status")
    public List<SubjectDTO> getAvailableSubject() {
        return subjectService.getAvailableSubject();
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createNewSubject(@Valid @RequestBody SubjectDTO subjectDTO){
         return subjectService.createSubject(subjectDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateSubject(@Valid @RequestBody SubjectDTO subjectDTO, @PathVariable int id){
        return subjectService.updateSubject(subjectDTO,id);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ResponseDTO> updateSubjectByStatus(@Valid @RequestBody SubjectDTO subjectDTO, @PathVariable int id){
        return subjectService.updateSubjectByStatus(subjectDTO,id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@Valid @PathVariable int id){
        subjectService.deleteSubject(id);
    }
}
