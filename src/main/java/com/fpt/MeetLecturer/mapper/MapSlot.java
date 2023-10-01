package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Slot;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MapSlot {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        //Define the mapping configuration for Slot to SlotDTO
        TypeMap<Slot, SlotDTO> slotToDTOTypeMap = modelMapper.createTypeMap(Slot.class, SlotDTO.class)
                .addMapping(src -> src.getLocation().getId(), SlotDTO::setLocationId)
                .addMapping(src -> src.getLocation().getName(), SlotDTO::setLocationName);
//                .addMapping(src -> MapSubject.convertListToSubjectDTO(src.getLikedSubjects()), SlotDTO::setSubjectList);
    }

    public SlotDTO convertSlotToSlotDTO(Optional<Slot> slot){
        return modelMapper.map(slot, SlotDTO.class);
    }

    public List<SlotDTO> convertListToSlotDTO(List<Slot> slots){
        List<SlotDTO> list = new ArrayList<>();
        slots.forEach(slot -> list.add(convertSlotToSlotDTO(Optional.ofNullable(slot))));
        return list;
    }
    public Slot convertSlotDTOToSlot(SlotDTO slotDTO){
        return modelMapper.map(slotDTO, Slot.class);
    }



}
