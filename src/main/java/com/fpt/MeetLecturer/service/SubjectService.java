package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.entity.Subject;
import com.fpt.MeetLecturer.mapper.GenericMap;
import com.fpt.MeetLecturer.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GenericMap genericMap;


    public List<SubjectDTO> getAllSubject() {
        return genericMap.ToDTOList(subjectRepository.findAll(), SubjectDTO.class);
    }

    public List<SubjectDTO> getAvailableSubject() {
        return genericMap.ToDTOList(subjectRepository.findByStatus(true), SubjectDTO.class);
    }

    public SubjectDTO getSubjectById(int id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            Subject existingBooking = subject.get();
            return genericMap.ToDTO(existingBooking, SubjectDTO.class);
        } else {
            throw new RuntimeException("can't find this subject slot by id");
        }

    }

    public ResponseEntity<ResponseDTO> updateSubjectByStatus(SubjectDTO subjectDTO, int id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            Subject subject1 = subject.get();
            subject1.setStatus(subjectDTO.isStatus());
            subjectRepository.save(subject1);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Update subject status successfully", "")
            );
        } else {
            throw new RuntimeException("Can't find this subject with id: " + id);
        }
    }

    public ResponseEntity<ResponseDTO> createSubject(SubjectDTO subjectDTO) {
        Optional<Subject> booking1 = subjectRepository.findById(subjectDTO.getId());
        if (booking1.isPresent()) {
            throw new IllegalStateException("this subject have already existed");
        }
        subjectRepository.save(genericMap.ToEntity(subjectDTO, Subject.class));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO(HttpStatus.OK, "Create successfully", "")
        );
    }

    public ResponseEntity<ResponseDTO> updateSubject(SubjectDTO subjectDTO, int id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            Subject subject1 = subject.get();
            subject1.setName(subjectDTO.getName());
            subject1.setSemester(subjectDTO.getSemester());
            subjectRepository.save(subject1);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Create successfully", "")
            );
        } else {
            throw new RuntimeException("Can't find this subject with id: " + id);
        }
    }


    public void deleteSubject(int id) {
        Optional<Subject> SubjectOptional = subjectRepository.findById(id);
        if (SubjectOptional.isPresent()) {
            Subject existSubject = SubjectOptional.get();
            existSubject.setStatus(false);
            subjectRepository.save(existSubject);
        } else {
            throw new IllegalStateException("student with id " + id + " does not exists");
        }
    }
}
