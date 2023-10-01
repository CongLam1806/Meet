package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapSubject {
    private static final ModelMapper modelMapper = new ModelMapper();
    public static SubjectDTO convertSubjectToSubjectDTO(Subject subject){
        return modelMapper.map(subject, SubjectDTO.class);
    }

    public static List<SubjectDTO> convertListToSubjectDTO(List<Subject> subjects){
        List<SubjectDTO> list = new ArrayList<>();
        if(subjects == null){
            return null;
        }
        subjects.forEach(subject -> list.add(convertSubjectToSubjectDTO(subject)));
        return list;
    }

    public static Subject convertSubjectDTOToSubject(SubjectDTO subjectDTO){
        return modelMapper.map(subjectDTO, Subject.class);
    }
}
