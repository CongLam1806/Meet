package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapSubject {
    private static final ModelMapper modelMapper = new ModelMapper();

    public SubjectDTO convertEntitytoDTO(Subject subject){
        return modelMapper.map(subject, SubjectDTO.class);
    }

    public List<SubjectDTO> convertListToSubjectDto(List<Subject> subjects){
        List<SubjectDTO> list = new ArrayList<>();
        subjects.forEach(subject -> list.add(convertEntitytoDTO(subject)));
        return list;
    }

    public Subject convertBookingDTOtoBooking(SubjectDTO subjectDTO){
        return  modelMapper.map(subjectDTO, Subject.class);
    }

    public List<Subject> convertListToLecturer(List<SubjectDTO> subjectDTOS){
        List<Subject> list = new ArrayList<>();
        subjectDTOS.forEach(subjectDTO -> list.add(convertBookingDTOtoBooking(subjectDTO)));
        return list;
    }

    public static Subject convertSubjectDTOToSubject(SubjectDTO subjectDTO){
        return modelMapper.map(subjectDTO, Subject.class);
    }
}
