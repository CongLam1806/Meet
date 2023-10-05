package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.StudentDTO;
import com.fpt.MeetLecturer.service.SlotService;
import com.fpt.MeetLecturer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/get")
    public List<StudentDTO> getStudent(){

        return studentService.get();
    }

    @PostMapping("/post")
    public void createNew(@RequestBody StudentDTO model){
        studentService.createStudent(model);
    }


    @PutMapping("/put/{id}")
    public void updateStudent(@RequestBody StudentDTO model, @PathVariable("id") int id){

        studentService.updateStudent(model);
        //System.out.println("OK");
    }
}
