package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapSlot {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        //Define the mapping configuration for Slot to SlotDTO
        TypeMap<Slot, SlotDTO> slotToDTOTypeMap = modelMapper.createTypeMap(Slot.class, SlotDTO.class)
                .addMapping(src -> src.getLocation().getId(), SlotDTO::setLocationId)
                .addMapping(src -> src.getLocation().getName(), SlotDTO::setLocationName)
                .addMapping(src -> MapSubject.convertListToSubjectDTO(src.getLikedSubjects()), SlotDTO::setSubjectList);
    }

    public static SlotDTO convertSlotToSlotDTO(Slot slot){
        return modelMapper.map(slot, SlotDTO.class);
    }

    public static List<SlotDTO> convertListToSlotDTO(List<Slot> slots){
        List<SlotDTO> list = new ArrayList<>();
        slots.forEach(slot -> list.add(convertSlotToSlotDTO(slot)));
        return list;
    }
    public static Slot convertSlotDTOToSlot(SlotDTO slotDTO){
        return modelMapper.map(slotDTO, Slot.class);
    }

}
