package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.entity.Subject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapSubject {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        TypeMap<Subject, SubjectDTO> propertyMapper = modelMapper.createTypeMap(Subject.class, SubjectDTO.class);
        propertyMapper
//                .addMapping(src -> src.getId(), LecturerDTO::setSubjectList);
                .addMapping(Subject::getMajorList, SubjectDTO::setMajorList);
    }

}
