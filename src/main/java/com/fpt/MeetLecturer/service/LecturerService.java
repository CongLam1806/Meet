package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.businessModel.LecturerDTO;
import com.fpt.MeetLecturer.entityModel.Lecturer;
import com.fpt.MeetLecturer.mapper.MapLecturer;
import com.fpt.MeetLecturer.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private MapLecturer mapLecturer;


    //get all lecturer
    public List<LecturerDTO> getAllLecturer() {
//        return lecturerRepository.findAll();
        return mapLecturer.convertListToLecturerDto(lecturerRepository.findAll());
    }

    //get lecturer by email
    public LecturerDTO getLecturerByEmail(String email) {
        return mapLecturer.convertLecturertoLecturerDTO(lecturerRepository.findByUserEmail(email));
    }

    public LecturerDTO updateLecturer(LecturerDTO newLecturer, int id) {
        Optional<Lecturer> optionalLecturer = lecturerRepository.findById(id);
        if (optionalLecturer.isPresent()) {
            Lecturer existingLecturer = optionalLecturer.get();
            existingLecturer.setNote(newLecturer.getNote());
            existingLecturer.setPhone(newLecturer.getPhone());
            lecturerRepository.save(existingLecturer);
            return mapLecturer.convertLecturertoLecturerDTO(existingLecturer);
        } else {
            newLecturer.setId(id);
            Lecturer lecturer = mapLecturer.convertLecturerDTOtoLecturer(newLecturer);
            return mapLecturer.convertLecturertoLecturerDTO(lecturer);
        }
    }

}

