package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.User;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.mapper.MapUser;
import com.fpt.MeetLecturer.repository.SlotRepositoty;
import com.fpt.MeetLecturer.repository.UserRepository;
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
    @Autowired(required = false)
    private ModelMapper modelMapper;

    public List<SlotDTO> get(){
        return mapSlot.convertListToSlotDTO(slotRepository.findAll());
    }

    public SlotDTO updateSlot(SlotDTO newSlot){
        Optional<Slot> optionalSlot = slotRepository.findById(newSlot.getId());
        if (optionalSlot.isPresent()) {
            Slot existingSlot = optionalSlot.get();
            existingSlot.setPassword(newSlot.getPassword());
            existingSlot.setStatus(newSlot.isStatus());
            existingSlot.setEndTime(newSlot.getEndTime());
            existingSlot.setStartTime(newSlot.getStartTime());

            slotRepository.save(existingSlot);
            return mapSlot.convertSlotToSlotDTO(existingSlot);
        } else {

            Slot slot = mapSlot.convertSlotDTOToSlot(newSlot);
            return mapSlot.convertSlotToSlotDTO(slot);
        }
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
