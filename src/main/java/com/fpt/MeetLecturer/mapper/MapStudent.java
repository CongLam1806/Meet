package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.StudentDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MapStudent {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        //Define the mapping configuration for Slot to SlotDTO
        TypeMap<Student, StudentDTO> studentToDTOTypeMap = modelMapper.createTypeMap(Student.class, StudentDTO.class)
                .addMapping(src -> src.getMajor().getName(), StudentDTO::setMajorName);





        //.addMapping(src -> src.getUser().getName(), SlotDTO::setLecturerName);

    }


    public StudentDTO convertStudentToStudentDTO(Student student){
        return  modelMapper.map(student, StudentDTO.class);


    }

    public List<StudentDTO> convertListToStudentDTO(List<Student> students){
        List<StudentDTO> list = new ArrayList<>();
        students.forEach(student -> list.add(convertStudentToStudentDTO(student)));
        return list;
    }
}
