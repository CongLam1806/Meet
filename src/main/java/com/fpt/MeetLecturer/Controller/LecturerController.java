package com.fpt.MeetLecturer.Controller;

import com.fpt.MeetLecturer.EntityModel.Lecturer;
import com.fpt.MeetLecturer.Service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    @GetMapping("{email}")
    public List<Lecturer> getLecturerByEmail(@PathVariable String email){
        return lecturerService.getAllLecturer();
    }

    @GetMapping("")
    public List<Lecturer> getAllLecturer(){
        return lecturerService.getAllLecturer();
    }

    @PutMapping("/{id}")
    public Lecturer updateLecturer(@RequestBody Lecturer newLecturer, @PathVariable int id ){
        return lecturerService.updateLecturer(newLecturer, id);
    }

}
