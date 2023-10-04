package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.MajorDTO;
import com.fpt.MeetLecturer.entity.Major;
import com.fpt.MeetLecturer.mapper.GenericMap;
import com.fpt.MeetLecturer.repository.MajorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        return genericMap.convertEntityListToDTOList(majorRepository.findAll(),MajorDTO.class);
    }


    public MajorDTO getMajorById(int id){
        Optional<Major> major = majorRepository.findById(id);
        if (major.isPresent()){
            Major existingBooking = major.get();
            return genericMap.convertEntityToDTO(existingBooking, MajorDTO.class);
        } else {
            throw new RuntimeException("can't find this major slot by id");
        }

    }

    public void createMajor(MajorDTO subjectDTO){
        Optional<Major> major = majorRepository.findById(subjectDTO.getId());
        if (major.isPresent()){
            throw new IllegalStateException("this subject has already booked");
        } else {
            Major major1 = new ModelMapper().map(subjectDTO, Major.class);
            majorRepository.save(major1);
        }
    }


//    public void deleteMajor(int id) {
//        Optional<Major> SubjectOptional = majorRepository.findById(id);
//        if (SubjectOptional.isPresent()){
//            Major existMajor = SubjectOptional.get();
//            existMajor.se(false);
//            majorRepository.save(existMajor);
//        } else {
//            throw new IllegalStateException("student with id " + id + " does not exists");
//        }
//
//    }
}
