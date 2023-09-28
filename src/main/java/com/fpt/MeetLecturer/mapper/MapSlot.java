package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapSlot {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static Slot convertSlotDTOToSlot(SlotDTO slotDTO){
        return modelMapper.map(slotDTO, Slot.class);
    }

    public static List<Slot> convertListToSlot(List<SlotDTO> slotsDTO){
        List<Slot> list = new ArrayList<>();
        slotsDTO.forEach(slotDTO -> list.add(convertSlotDTOToSlot(slotDTO)));
        return list;
    }

    public static SlotDTO convertSlotToSlotDTO(Slot slot){
        return modelMapper.map(slot, SlotDTO.class);
    }

    public static List<SlotDTO> convertListToSlotDTO(List<Slot> slots){
        List<SlotDTO> list = new ArrayList<>();
        slots.forEach(slot -> list.add(convertSlotToSlotDTO(slot)));
        return list;
    }

}
