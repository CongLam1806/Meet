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
@RequestMapping(path="/api")
public class SlotController {
    @Autowired
    private SlotService slotService;

    @GetMapping("/slot")
    public List<Slot> getSlot(){
        return slotService.get();
    }


    @PutMapping("/slot/put/{id}")
    public SlotDTO createNew(@RequestBody SlotDTO model, @PathVariable("id") int id){
        model.setId(id);
        return slotService.updateSlot(model);

    }

    @DeleteMapping("/slot/delete/{id}")
    public boolean deleteUser(@RequestParam(value = "id") int id) {
        return slotService.deleteSlot(id);
    }
}
