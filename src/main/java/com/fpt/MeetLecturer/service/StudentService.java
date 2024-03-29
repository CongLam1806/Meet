package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.StudentDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Student;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.mapper.MapStudent;
import com.fpt.MeetLecturer.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired(required = false)
    private StudentRepository studentRepository;

    @Autowired(required = false)
    private MapStudent mapStudent;

    private ModelMapper modelMapper = new ModelMapper();

    public ResponseDTO getAllStudent(){
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "FOUND ALL STUDENTS", mapStudent.convertListToStudentDTO(studentRepository.findAll()));
        return responseDTO;
    }

    public ResponseDTO getActiveStudentEmail(){
        List<Student> students = studentRepository.findByStatus(true);
        List<String> studentEmails = new ArrayList<>();
        students.forEach(student -> {
            studentEmails.add(student.getEmail());
        });

        return new ResponseDTO(HttpStatus.OK, "FOUND ALL STUDENT EMAILS", studentEmails);
    }

    public ResponseDTO getStudentById(String id){
        return new ResponseDTO(HttpStatus.OK, "FOUND STUDENT", mapStudent.convertStudentToStudentDTO(studentRepository.findById(id).orElseThrow()));
    }



    public ResponseDTO updateStudent(StudentDTO studentDTO){
        Student student =  studentRepository.findById(studentDTO.getId()).orElseThrow();
        student.setDob(studentDTO.getDob());
        student.setPhone(studentDTO.getPhone());
        student.setAddress(studentDTO.getAddress());
        studentRepository.save(student);
        return new ResponseDTO(HttpStatus.OK, "UPDATE STUDENT SUCCESSFULLY", mapStudent.convertStudentToStudentDTO(student));
    }

}
