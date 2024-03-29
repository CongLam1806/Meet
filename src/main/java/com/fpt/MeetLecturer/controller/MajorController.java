package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.MajorDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/status")
    public List<MajorDTO> getAllAvailableMajor(){
        return majorService.getAllAvalableMajor();
    }


    @PostMapping("")
    public ResponseEntity<ResponseDTO> CreateNewMajor(@RequestBody MajorDTO dto){
        return majorService.createMajor(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> UpdateMajor(@RequestBody MajorDTO dto
                                                                ,@PathVariable int id){
        return majorService.updateMajor(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> DeleteMajor(@PathVariable int id, @RequestParam boolean status){
        return  majorService.deleteMajor(id, status);
    }

}
