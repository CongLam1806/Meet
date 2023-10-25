package com.fpt.MeetLecturer.service;


import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.business.Subject_LecturerDTO;
import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Subject_Lecturer;
import com.fpt.MeetLecturer.mapper.GenericMap;
import com.fpt.MeetLecturer.mapper.MapLecturer;
import com.fpt.MeetLecturer.repository.LecturerRepository;
import com.fpt.MeetLecturer.repository.SubjectLecturerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private SubjectLecturerRepo subjectLecturerRepo;

    @Autowired
    private GenericMap genericMap;

    @Autowired
    private MapLecturer mapLecturer;

    @Autowired
    private SubjectLecturerService subjectLecturerService;


    //get all lecturer
    public List<LecturerDTO> getAllLecturer() {
        return mapLecturer.convertListToLecturerDTO(lecturerRepository.findAll());
    }

    public LecturerDTO getLecturerById(String id) {

        return mapLecturer.ToLecturerDTO(lecturerRepository.findById(id).orElseThrow());
    }

    public List<LecturerDTO> getAllLecturerByStatus() {
        return genericMap.ToDTOList(lecturerRepository.findByStatus(true), LecturerDTO.class);
    }

//    public ResponseEntity<ResponseDTO> createLecturer(LecturerDTO LecturerDTO) {
//        Lecturer lecturer = genericMap.ToEntity(LecturerDTO, Lecturer.class);
//        lecturerRepository.save(lecturer);
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseDTO(HttpStatus.OK, "Create successfully", "")
//        );
//
//    }

    @Transactional
    public ResponseEntity<ResponseDTO> updateLecturer(LecturerDTO newLecturer, String id) {
        Lecturer LecturerEntity = genericMap.ToEntity(newLecturer, Lecturer.class);
        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(id);
        if (optionalLecturer.isPresent()) {
            subjectLecturerRepo.deleteAllByLecturerId(id);

            for (Subject_LecturerDTO a : newLecturer.getSubjectList()) {
                subjectLecturerService.updateSubjectLecturer(id, a.getSubjectId());
            }
            Lecturer existingLecturer = optionalLecturer.get();
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

    public ResponseEntity<ResponseDTO> deleteLecturer(String id) {
        Optional<Lecturer> lecturerOptional = lecturerRepository.findById(id);
        if (lecturerOptional.isPresent()) {
            Lecturer lecturer = lecturerOptional.get();
            lecturer.setStatus(false);
            lecturerRepository.save(lecturer);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Delete successfully", "")
            );
        } else {
            throw new IllegalStateException("lecturer with id " + id + " does not exists");
        }

    }

}

