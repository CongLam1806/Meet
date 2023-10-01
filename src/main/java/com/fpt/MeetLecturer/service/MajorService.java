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

    public void createSubject(MajorDTO subjectDTO){
        Optional<Major> major = majorRepository.findById(subjectDTO.getId());
        if (major.isPresent()){
            throw new IllegalStateException("this subject has already booked");
        } else {
            majorRepository.save(mapMajor.convertDTOToEntity(subjectDTO, Major.class));
        }
    }

    public void updateSubject(MajorDTO subjectDTO){
        Optional<Subject> subject = majorRepository.findById(subjectDTO.getId());
        if (subject.isPresent()){
            Subject subject1 = subject.get();
            subject1.setName(subjectDTO.getName());
            subject1.setSemester(subjectDTO.getSemester());
            majorRepository.save(subject1);
        } else {
            throw new RuntimeException("Can't find this subjectDTO slot");
        }
    }

    public void deleteBooking(int id) {
        Optional<Subject> SubjectOptional = majorRepository.findById(id);
        if (SubjectOptional.isPresent()){
            Subject existSubject = SubjectOptional.get();
            existSubject.setStatus(false);
            majorRepository.save(existSubject);
        } else {
            throw new IllegalStateException("student with id " + id + " does not exists");
        }

    }
}
