package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.MajorDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.entity.Location;
import com.fpt.MeetLecturer.entity.Major;
import com.fpt.MeetLecturer.entity.Subject;
import com.fpt.MeetLecturer.mapper.GenericMap;
import com.fpt.MeetLecturer.repository.MajorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MajorService {

    @Autowired
    private MajorRepository majorRepository;


    @Autowired
    private GenericMap genericMap;
    public List<MajorDTO> getAllMajor(){
        return genericMap.ToDTOList(majorRepository.findAll(),MajorDTO.class);
    }

    public List<MajorDTO> getAllAvalableMajor(){
        return genericMap.ToDTOList(majorRepository.findByStatus(true), MajorDTO.class);
    }

    public ResponseEntity<ResponseDTO> createMajor(MajorDTO subjectDTO){
        Optional<Major> major = majorRepository.findById(subjectDTO.getId());
        if (major.isPresent()){
            throw new IllegalStateException("this subject has already booked");
        } else {
            Major major1 = new ModelMapper().map(subjectDTO, Major.class);
            majorRepository.save(major1);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Create major status successfully", "")
            );
        }
    }

    public ResponseEntity<ResponseDTO> updateMajor(MajorDTO majorDTO, int id) {
        Optional<Major> major = majorRepository.findById(id);
        if (major.isPresent()) {
            Major existMajor = major.get();
            existMajor.setName(majorDTO.getName());
            majorRepository.save(existMajor);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Update major status successfully", "")
            );
        } else {
            throw new RuntimeException("Can't find this major with id: " + id);
        }
    }

    public ResponseEntity<ResponseDTO> deleteMajor(int id){
        Optional<Major> major = majorRepository.findById(id);
        if(major.isPresent()){
            Major existMajor = major.get();
            existMajor.setStatus(false);
            majorRepository.save(existMajor);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Delete successfully", "")
            );
        }
        else {
            throw new IllegalStateException("Major with id " + id + " does not exists");
        }
    }




}
