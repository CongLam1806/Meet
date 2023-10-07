package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.User;
import com.fpt.MeetLecturer.service.SlotService;
import com.fpt.MeetLecturer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/slot")
public class SlotController {
    @Autowired
    private SlotService slotService;

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getSlot(){

        return ResponseEntity.ok().body(slotService.getSlot());
    }

//    @GetMapping("/get")
//    public ResponseEntity<ResponseDTO> getSlotBy(){
//
//        return ResponseEntity.ok().body(slotService.getSlot());
//    }

    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> createNew(@RequestBody SlotDTO model){

        return ResponseEntity.ok().body(slotService.createSlot(model));
    }


    @PutMapping("/put/{id}")
    public ResponseEntity<ResponseDTO> updateSlot(@RequestBody SlotDTO model, @PathVariable("id") int id){

        return ResponseEntity.ok().body(slotService.updateSlot(model));
        //System.out.println("OK");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(slotService.deleteSlot(id)) ;
    }
}
