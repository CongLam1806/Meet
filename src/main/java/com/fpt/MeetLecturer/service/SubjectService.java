package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.SubjectDTO;
import com.fpt.MeetLecturer.entity.Subject;
import com.fpt.MeetLecturer.mapper.MapSubject;
import com.fpt.MeetLecturer.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MapSubject mapSubject;


    public List<SubjectDTO> getAllSubject(){
        return mapSubject.convertListToSubjectDto(subjectRepository.findAll());
    }


    public List<SubjectDTO> getAvailableSubject(){
        return mapSubject.convertListToSubjectDto(subjectRepository.findByStatus(true));
    }
    public SubjectDTO getSubjectById(String id){
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()){
            Subject existingBooking = subject.get();
            return mapSubject.convertEntitytoDTO(existingBooking);
        } else {
            throw new RuntimeException("can't find this subject slot by id");
        }

    }

    public void createSubject(SubjectDTO subjectDTO){
        Optional<Subject> booking1 = subjectRepository.findById(subjectDTO.getId());
        if (booking1.isPresent()){
            throw new IllegalStateException("this subject has already booked");
        } else {
            subjectRepository.save(mapSubject.convertBookingDTOtoBooking(subjectDTO));
        }
    }

    public void updateSubject(SubjectDTO subjectDTO, String id){
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()){
            Subject subject1 = subject.get();
            subject1.setName(subjectDTO.getName());
            subject1.setSemester(subjectDTO.getSemester());
            subjectRepository.save(subject1);
        } else {
            throw new RuntimeException("Can't find this subjectDTO slot");
        }
    }

//    public void updateSubject(SubjectDTO subjectDTO, int id){
//        Optional<Subject> subject = subjectRepository.findById(id);
//        if (subject.isPresent()){
//            Subject subject1 = new ModelMapper().map(subjectDTO, Subject.class);
//            subjectRepository.save(subject1);
//        } else {
//            throw new RuntimeException("Can't find this subjectDTO slot");
//        }
//    }

    public void deleteSubject(String id) {
        Optional<Subject> SubjectOptional = subjectRepository.findById(Integer.valueOf((id)));
        if (SubjectOptional.isPresent()){
            Subject existSubject = SubjectOptional.get();
            existSubject.setStatus(false);
            subjectRepository.save(existSubject);
        } else {
            throw new IllegalStateException("student with id " + id + " does not exists");
        }

    }
}
