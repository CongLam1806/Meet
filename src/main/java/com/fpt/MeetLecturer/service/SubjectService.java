package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.*;
import com.fpt.MeetLecturer.entity.Major;
import com.fpt.MeetLecturer.entity.Subject;
import com.fpt.MeetLecturer.entity.Subject_Major;
import com.fpt.MeetLecturer.mapper.GenericMap;
import com.fpt.MeetLecturer.repository.SubjectMajorRepo;
import com.fpt.MeetLecturer.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GenericMap genericMap;

    @Autowired
    private SubjectMajorRepo subjectMajorRepo;

    @Autowired
    private SubjectMajorService subjectMajorService;

    @Autowired
    private MajorService majorService;


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
            throw new RuntimeException("can't find this subject with id" + id);
        }

    }

    public SubjectDTO getSubjectByCode(String code) {
        Subject subject = subjectRepository.findByCode(code);
        if (subject != null) {
            return genericMap.ToDTO(subject, SubjectDTO.class);
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

    @Transactional
    public ResponseEntity<ResponseDTO> updateSubject(SubjectDTO subjectDTO, int id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            subjectMajorRepo.deleteAllBySubjectId(id);

            for (Subject_MajorDTO subjectMajorDTO : subjectDTO.getMajorList()) {
                subjectMajorService.updateSubject_Major(id, subjectMajorDTO.getMajorId());
            }

            Subject subject1 = subject.get();
            if (subjectDTO.getCode().trim().equalsIgnoreCase(subject.get().getCode().trim())){
                subject1.setCode(subjectDTO.getCode());
            }
            subject1.setName(subjectDTO.getName());
            subject1.setSemester(subjectDTO.getSemester());
            subjectRepository.save(subject1);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Update subject successfully", "")
            );
        } else {
            throw new RuntimeException("Can't find this subject with id: " + id);
        }
    }


    public ResponseEntity<ResponseDTO> createSubject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        try {
            Subject existSubject = subjectRepository.findByCode(subjectDTO.getCode());
            if (existSubject != null) {
                throw new IllegalStateException("This Subject code has already existed");
            }
        } catch (Exception ex) {
            throw new IllegalStateException("This Subject code has already existed");
        }

        subject.setCode(subjectDTO.getCode());
        subject.setName(subjectDTO.getName());
        subject.setSemester(subjectDTO.getSemester());
        subject.setStatus(subjectDTO.isStatus());

        // Save the subject first to generate its ID
        subject = subjectRepository.save(subject);

        // Create a new Subject_Major entity for each major and link it to the subject
        for (Subject_MajorDTO subjectMajorDTO : subjectDTO.getMajorList()) {
            Major major = new Major();
            major.setId(subjectMajorDTO.getMajorId());
            major.setName(subjectMajorDTO.getMajorName());

            Subject_Major subjectMajor = new Subject_Major(major, subject);

            // Save the subject_major
            subjectMajor.getSubjectMajorKey().setSubjectId(subject.getId());
            subjectMajorRepo.save(subjectMajor);
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO(HttpStatus.OK, "Subject created successfully", "")
        );
    }



    public ResponseEntity<ResponseDTO> deleteSubject(int id) {
        Optional<Subject> SubjectOptional = subjectRepository.findById(id);
        if (SubjectOptional.isPresent()) {
            Subject existSubject = SubjectOptional.get();
            existSubject.setStatus(false);
            subjectRepository.save(existSubject);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Delete successfully", "")
            );
        } else {
            throw new IllegalStateException("student with id " + id + " does not exists");
        }
    }
}
