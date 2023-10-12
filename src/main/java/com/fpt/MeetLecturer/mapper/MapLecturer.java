package com.fpt.MeetLecturer.mapper;


import com.fpt.MeetLecturer.business.AccountDTO;
import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.entity.Account;
import com.fpt.MeetLecturer.entity.Lecturer;

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

    public Lecturer convertLecturer(LecturerDTO lecturerDTO){
        return modelMapper.map(lecturerDTO, Lecturer.class);
    }

    public List<Lecturer> convertListToLecturer(List<LecturerDTO> lecturerDTOS){
        List<Lecturer> list = new ArrayList<>();
        lecturerDTOS.forEach(lecturerDTO -> list.add(convertLecturer(lecturerDTO)));
        return list;
    }

    public  LecturerDTO ToLecturerDTO(Lecturer account){
        return modelMapper.map(account, LecturerDTO.class);
    }

    public List<LecturerDTO> convertListToLecturerDTO(List<Lecturer> accounts){
        List<LecturerDTO> list = new ArrayList<>();
        accounts.forEach(lecturer -> list.add(ToLecturerDTO(lecturer)));
        return list;
    }





}
