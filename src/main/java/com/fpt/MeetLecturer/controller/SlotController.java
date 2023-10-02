package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.User;
import com.fpt.MeetLecturer.service.SlotService;
import com.fpt.MeetLecturer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/slot")
public class SlotController {
    @Autowired
    private SlotService slotService;

    @GetMapping("/get")
    public List<SlotDTO> getSlot(){
        return slotService.get();
    }

    @PostMapping("/post")
    public void createNew(@RequestBody SlotDTO model){
        slotService.createSlot(model);
    }


    @PutMapping("/put/{id}")
    public void updateSlot(@RequestBody SlotDTO model, @PathVariable("id") int id){

        slotService.updateSlot(model);
        //System.out.println("OK");
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable("id") int id) {
        return slotService.deleteSlot(id);
    }
}
