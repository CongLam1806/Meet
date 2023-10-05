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
                //.addMapping(src -> src.getUser().getId(), LecturerDTO::setUserid)
                .addMapping(Lecturer::getSubject, LecturerDTO::setSubjectName);
    }

    public LecturerDTO convertLecturertoLecturerDTO(Lecturer lecturer){
        return modelMapper.map(lecturer, LecturerDTO.class);
    }

    public List<LecturerDTO> convertListToLecturerDto(List<Lecturer> lecturers){
        List<LecturerDTO> list = new ArrayList<>();
        lecturers.forEach(lecturer -> list.add(convertLecturertoLecturerDTO(lecturer)));
        return list;
    }

    public Lecturer convertLecturerDTOtoLecturer(LecturerDTO lecturerDTO){
        return  modelMapper.map(lecturerDTO, Lecturer.class);
    }

    public List<Lecturer> convertListToLecturer(List<LecturerDTO> lecturerDTOS){
        List<Lecturer> list = new ArrayList<>();
        lecturerDTOS.forEach(lecturerDTO -> list.add(convertLecturerDTOtoLecturer(lecturerDTO)));
        return list;
    }




}
