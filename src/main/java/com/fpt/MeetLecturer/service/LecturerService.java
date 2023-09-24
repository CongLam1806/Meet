package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.model.Lecturer;
import com.fpt.MeetLecturer.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    //get all lecturer
    public List<Lecturer> getAllLecturer(){
        return lecturerRepository.findAll();
    }

    //get lecturer by email
    public Lecturer getLecturerByEmail(String email) {
        return lecturerRepository.findByUserEmail(email);
    }

    public Lecturer updateLecturer(@RequestBody Lecturer newLecturer, @PathVariable int id){
        Lecturer updatedLecturer;
        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(id);
        if (optionalLecturer.isPresent()){
            Lecturer existingLecturer = optionalLecturer.get();
            existingLecturer.setNote(newLecturer.getNote());
            existingLecturer.setPhone(newLecturer.getPhone());
            updatedLecturer = lecturerRepository.save(existingLecturer);
        } else {
            newLecturer.setId(id);
            updatedLecturer = lecturerRepository.save(newLecturer);
        }
        return updatedLecturer;
    }
}
