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
    }

    public void updateSlot(SlotDTO newSlot){

        Slot slot;
        if (newSlot.getId() == 0) {
           slot = new Slot();
        } else {
            slot = slotRepository.findById(newSlot.getId()).orElseThrow();

        }
        modelMapper.map(newSlot, slot);

        slotRepository.save(slot);

    }

    public boolean deleteSlot(int id) {
        Optional<Slot> slot = slotRepository.findById(id);
        if (slot.isEmpty()) {
            return false;
        } else {
            //User delUser = user1.get();
            //userRepository.delete(user1.get());
            if (!slot.get().isStatus()) {
                return false;
            }
            slot.get().setStatus(false);
            slotRepository.save(slot.get());
            //mapUser.mapUserToUserDTO(delUser);
            return true;
        }
    }
}
