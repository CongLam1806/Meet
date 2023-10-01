package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.MajorDTO;
import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.entity.Major;
import com.fpt.MeetLecturer.entity.Subject;
import com.fpt.MeetLecturer.mapper.MapMajor;
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
    private MapMajor mapMajor;
    public List<MajorDTO> getAllMajor(){
        return mapMajor.convertEntityListToDTOList(majorRepository.findAll(),MajorDTO.class);
    }


    public MajorDTO getMajorById(int id){
        Optional<Major> major = majorRepository.findById(id);
        if (major.isPresent()){
            Major existingBooking = major.get();
            return mapMajor.convertEntityToDTO(existingBooking, MajorDTO.class);
        } else {
            throw new RuntimeException("can't find this major slot by id");
        }

    }

    public void createMajor(MajorDTO subjectDTO){
        Optional<Major> major = majorRepository.findById(subjectDTO.getId());
        if (major.isPresent()){
            throw new IllegalStateException("this subject has already booked");
        } else {
            majorRepository.save(mapMajor.convertDTOToEntity(subjectDTO, Major.class));
        }
    }


//    public void deleteMajor(int id) {
//        Optional<Major> SubjectOptional = majorRepository.findById(id);
//        if (SubjectOptional.isPresent()){
//            Major existMajor = SubjectOptional.get();
//            existMajor.setStatus(false);
//            majorRepository.save(existMajor);
//        } else {
//            throw new IllegalStateException("student with id " + id + " does not exists");
//        }
//
//    }
}
