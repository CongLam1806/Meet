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

    @GetMapping("")
    public List<SlotDTO> getSlot(SlotDTO slotDTO){
        return slotService.get();
    }


    @PutMapping("/put/{id}")
    public SlotDTO createNew(@RequestBody SlotDTO model, @PathVariable("id") int id){
        model.setId(id);
        return slotService.updateSlot(model);

    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@RequestParam(value = "id") int id) {
        return slotService.deleteSlot(id);
    }
}
