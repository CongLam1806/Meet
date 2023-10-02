package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.repository.SlotRepositoty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SlotService {
    @Autowired(required = false)
    private SlotRepositoty slotRepository;

    @Autowired(required = false)
    private MapSlot mapSlot;

    private ModelMapper modelMapper = new ModelMapper();

    public List<SlotDTO> get(){
        return mapSlot.convertListToSlotDTO(slotRepository.findAll());
        //System.out.println(slotRepository.findAll());
        //return slotRepository.findAll();
    }

    public void createSlot(SlotDTO newSlot){

        Slot slot = new Slot();
        modelMapper.map(newSlot, slot);
        slotRepository.save(slot);
    }

    public void updateSlot(SlotDTO newSlot){
        Slot slot;
        slot = slotRepository.findById(newSlot.getId()).orElseThrow();
        modelMapper.map(newSlot, slot);
        slotRepository.save(slot);
    }

    public boolean deleteSlot(int id) {
        Slot slot = slotRepository.findById(id).orElseThrow();
        if (slot.getId() == 0) {
            return false;
        } else {
            //User delUser = user1.get();
            //userRepository.delete(user1.get());
            if (!slot.isStatus()) {
                return false;
            }
            slot.setStatus(false);
            slotRepository.save(slot);
            //mapUser.mapUserToUserDTO(delUser);
            return true;
        }
    }
}
