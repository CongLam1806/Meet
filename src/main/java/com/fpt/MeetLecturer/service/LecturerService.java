package com.fpt.MeetLecturer.service;


import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.Subject_LecturerDTO;
import com.fpt.MeetLecturer.entity.Lecturer;

import com.fpt.MeetLecturer.entity.Subject;
import com.fpt.MeetLecturer.mapper.MapLecturer;
import com.fpt.MeetLecturer.repository.LecturerRepository;
import com.fpt.MeetLecturer.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MapLecturer mapLecturer;


    //get all lecturer
    public List<LecturerDTO> getAllLecturer() {
//        return lecturerRepository.findAll();
        return mapLecturer.convertListToLecturerDto(lecturerRepository.findAll());
    }

//    public ResponseEntity<ResponseDTO> getLecturerByEmail(String email) {
//        boolean exist = lecturerRepository.existsByUserEmail(email);
//        if (exist) {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseDTO(HttpStatus.OK, "Get successfully",
//                            mapLecturer.convertLecturertoLecturerDTO(lecturerRepository.findByUserEmail(email)))
//            );
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                new ResponseDTO(HttpStatus.NOT_FOUND, "Can't find this email", "")
//        );
//
//    }

    public ResponseEntity<ResponseDTO> createLecturer(LecturerDTO LecturerDTO) {
        Lecturer lecturer = new ModelMapper().map(LecturerDTO, Lecturer.class);
        lecturerRepository.save(lecturer);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO(HttpStatus.OK, "Create successfully", "")
        );

    }


    public ResponseEntity<ResponseDTO> updateLecturer(LecturerDTO newLecturer, int id) {
        Lecturer LecturerEntity = new ModelMapper().map(newLecturer, Lecturer.class);
        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(id);
        if (optionalLecturer.isPresent()) {
            Lecturer existingLecturer = optionalLecturer.get();
            //existingLecturer.setName(newLecturer.getName());
            existingLecturer.setNote(LecturerEntity.getNote());
            existingLecturer.setPhone(LecturerEntity.getPhone());
            lecturerRepository.save(existingLecturer);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Update successfully", "")
            );
        } else {
            throw new RuntimeException("Can't find lecturer with id: " + id);
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

