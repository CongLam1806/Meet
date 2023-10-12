package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.service.LecturerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

//    @GetMapping("/{email}")
//    public ResponseEntity<List<ResponseDTO>> getLecturerByEmail(@PathVariable String email){
//        return lecturerService.getLecturerByEmail(email);
//    }

    @GetMapping("")
    public List<LecturerDTO> getAllLecturer(){
        return lecturerService.getAllLecturer();
    }

    @GetMapping("/status")
    public List<LecturerDTO> getAllLecturerByStatus(){
        return lecturerService.getAllLecturerByStatus();
    }

    @GetMapping("/{email}")
    public ResponseEntity<ResponseDTO> getAllLecturerByEmail(@PathVariable String email){
        return lecturerService.getLecturerByEmail(email);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO> createLecturer(@RequestBody @Valid LecturerDTO lecturerDTO){
       return lecturerService.createLecturer(lecturerDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateLecturer(@RequestBody @Valid LecturerDTO newLecturer, @PathVariable String id ){
         return lecturerService.updateLecturer(newLecturer, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteLecturer(@PathVariable int id ){
        return lecturerService.deleteLecturer(id);
    }
}
