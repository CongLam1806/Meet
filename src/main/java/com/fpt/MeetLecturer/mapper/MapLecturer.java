package com.fpt.MeetLecturer.mapper;


import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Lecturer;

import com.fpt.MeetLecturer.entity.Slot;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapLecturer {



    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        TypeMap<Lecturer, LecturerDTO> propertyMapper = modelMapper.createTypeMap(Lecturer.class, LecturerDTO.class);
        propertyMapper
//                .addMapping(src -> src.getId(), LecturerDTO::setSubjectList);
                .addMapping(Lecturer::getSubjectList, LecturerDTO::setSubjectList);
    }





}
