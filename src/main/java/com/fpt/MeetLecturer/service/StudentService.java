package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.StudentDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Student;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.mapper.MapStudent;
import com.fpt.MeetLecturer.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired(required = false)
    private StudentRepository studentRepository;

    @Autowired(required = false)
    private MapStudent mapStudent;

    private ModelMapper modelMapper = new ModelMapper();

    public List<StudentDTO> get(){

        return mapStudent.convertListToStudentDTO(studentRepository.findAll());
    }

    public void createStudent(StudentDTO newStudent){

        Student student = new Student();
        modelMapper.map(newStudent, student);
        studentRepository.save(student);
    }

    public void updateStudent(StudentDTO newStudent){
        Student student;
        student = studentRepository.findById(newStudent.getId()).orElseThrow();
        modelMapper.map(newStudent, student);
        studentRepository.save(student);
    }

}
