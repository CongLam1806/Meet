package com.fpt.MeetLecturer.mapper;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.StudentDTO;
import com.fpt.MeetLecturer.business.UserDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class MapStudent {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        //Define the mapping configuration for Slot to SlotDTO
        TypeMap<Student, StudentDTO> userToDTOTypeMap = modelMapper.createTypeMap(Student.class, StudentDTO.class)
                .addMapping(src -> src.getUser(), StudentDTO::setUser);

        //.addMapping(src -> src.getUser().getName(), SlotDTO::setLecturerName);

    }

    public StudentDTO convertStudentToStudentDTO(Student student){
        return  modelMapper.map(student, StudentDTO.class);
        //slotDTO.setMeetingDate(new SimpleDateFormat("yyyy-MM-dd").format(slotDTO));

    }

    public List<StudentDTO> convertListToStudentDTO(List<Student> students){
        List<StudentDTO> list = new ArrayList<>();
        students.forEach(student -> list.add(convertStudentToStudentDTO(student)));
        return list;
    }
}
