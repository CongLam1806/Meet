package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.StudentDTO;
import com.fpt.MeetLecturer.service.SlotService;
import com.fpt.MeetLecturer.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllStudent(){
        ResponseDTO responseDTO = studentService.getAllStudent();
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/emails")
    public ResponseEntity<ResponseDTO> getStudentEmails(){
        ResponseDTO responseDTO = studentService.getActiveStudentEmail();
        return ResponseEntity.ok().body(responseDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateStudent(@RequestBody @Valid StudentDTO model, @PathVariable("id") String id){
        ResponseDTO responseDTO = studentService.updateStudent(model, id);
        return ResponseEntity.ok().body(responseDTO);
    }
}
