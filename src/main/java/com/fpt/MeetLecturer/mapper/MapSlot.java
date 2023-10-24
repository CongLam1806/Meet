package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Slot;
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

                .addMapping(src -> src.getSlotSubjects(), SlotDTO::setSlotSubjectDTOS)
                .addMapping(src -> src.getLecturer().getName(), SlotDTO::setLecturerName)
                .addMapping(src -> src.getLocation().getName(), SlotDTO::setLocationName);


    }

    public SlotDTO convertSlotToSlotDTO(Slot slot){
        return  modelMapper.map(slot, SlotDTO.class);
        //slotDTO.setMeetingDate(new SimpleDateFormat("yyyy-MM-dd").format(slotDTO));

    }

    public  List<SlotDTO> convertListToSlotDTO(List<Slot> slots){
        List<SlotDTO> list = new ArrayList<>();
        slots.forEach(slot -> list.add(convertSlotToSlotDTO(slot)));
        return list;
    }
    public Slot convertSlotDTOToSlot(SlotDTO slotDTO){
        return modelMapper.map(slotDTO, Slot.class);
    }


    public List<Slot> toSlotList(List<SlotDTO> slotdtos) {
        List<Slot> list = new ArrayList<>();
        slotdtos.forEach(slot -> list.add(convertSlotDTOToSlot(slot)));
        return list;
    }
}
