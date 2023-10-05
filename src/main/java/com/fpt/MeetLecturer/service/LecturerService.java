package com.fpt.MeetLecturer.service;


import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.entity.Lecturer;

import com.fpt.MeetLecturer.mapper.MapLecturer;
import com.fpt.MeetLecturer.repository.LecturerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private MapLecturer mapLecturer;


    //get all lecturer
    public List<LecturerDTO> getAllLecturer() {
//        return lecturerRepository.findAll();
        return mapLecturer.convertListToLecturerDto(lecturerRepository.findAll());
    }

    //get lecturer by email
    public LecturerDTO getLecturerByEmail(String email) {
        return mapLecturer.convertLecturertoLecturerDTO(lecturerRepository.findByUserEmail(email));
    }

    public void createLecturer(LecturerDTO LecturerDTO){
        Lecturer lecturer = new ModelMapper().map(LecturerDTO, Lecturer.class);
        lecturerRepository.save(lecturer);
    }

    public void updateLecturer(LecturerDTO newLecturer, int id) {
        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(id);
        if (optionalLecturer.isPresent()) {
            Lecturer existingLecturer = optionalLecturer.get();
            //existingLecturer.setName(newLecturer.getName());
            existingLecturer.setNote(newLecturer.getNote());
            existingLecturer.setPhone(newLecturer.getPhone());
            lecturerRepository.save(existingLecturer);
        } else {
            throw new RuntimeException();
        }
    }

//    public void updateLecturer(LecturerDTO newLecturer, int id) {
//        Lecturer lecturer;
//        if (lecturerRepository.findById(id).isEmpty()) {
//            lecturer = new Lecturer();
//        } else {
//            lecturer = lecturerRepository.findById(id)
//                    .orElseThrow(() -> new RuntimeException("Lecturer not found"));
//        }
//
//        new ModelMapper().map(newLecturer, lecturer);
//
//        lecturerRepository.save(lecturer);
//    }




}

