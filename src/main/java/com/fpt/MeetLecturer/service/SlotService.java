package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.repository.SlotRepositoty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotService {
    @Autowired(required = false)
    private SlotRepositoty slotRepository;

    @Autowired(required = false)
    private MapSlot mapSlot;






    private ModelMapper modelMapper = new ModelMapper();

    public ResponseDTO getSlot(){
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS", mapSlot.convertListToSlotDTO(slotRepository.findAll()));
        return responseDTO;

        //System.out.println(slotRepository.findAll());
        //return slotRepository.findAll();
    }

    public ResponseDTO createSlot(SlotDTO newSlot){
        Slot slot = new Slot();
        modelMapper.map(newSlot, slot);
        slotRepository.save(slot);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "CREATE SLOT SUCCESSFULLY", mapSlot.convertSlotToSlotDTO(slot));
        return responseDTO;
    }

    public ResponseDTO updateSlot(SlotDTO newSlot){
        Slot slot;
        slot = slotRepository.findById(newSlot.getId()).orElseThrow();
        modelMapper.map(newSlot, slot);
        slotRepository.save(slot);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "UPDATE SLOT SUCCESSFULLY", mapSlot.convertSlotToSlotDTO(slot));
        return responseDTO;
    }

    public ResponseDTO deleteSlot(int id) {
        boolean bool;
        Slot slot = slotRepository.findById(id).orElseThrow();
        if (slot.getId() == 0) {
            bool = false;
        } else {
            //User delUser = user1.get();
            //userRepository.delete(user1.get());
            if (!slot.isStatus()) {
                bool = false;
            }
            slot.setStatus(false);
            slotRepository.save(slot);
            //mapUser.mapUserToUserDTO(delUser);
            bool = true;
        }
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "DELETE SLOT SUCCESSFULLY", bool);
        return responseDTO;
    }
}
