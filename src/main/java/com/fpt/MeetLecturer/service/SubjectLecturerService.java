package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.entity.Subject;
import com.fpt.MeetLecturer.entity.SubjectLecturerKey;
import com.fpt.MeetLecturer.entity.Subject_Lecturer;
import com.fpt.MeetLecturer.repository.LecturerRepository;
import com.fpt.MeetLecturer.repository.SubjectRepository;
import com.fpt.MeetLecturer.repository.SubjectLecturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubjectLecturerService {
    @Autowired
    private SubjectLecturerRepo subjectLecturerRepo;
    @Autowired
    private LecturerRepository lecturerRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public void updateSubjectLecturer(String Lecturerid, int Subjectid) {
        Lecturer lecturer = lecturerRepository.findById(Lecturerid).orElse(null);
        Subject subject = subjectRepository.findById(Subjectid).orElseThrow();

// Check if both lecturer and subject exist
        if (lecturer != null) {
            SubjectLecturerKey subjectLecturerKey = new SubjectLecturerKey(lecturer.getId(), subject.getId());

            // Create a new Subject_Lecturer entity
            Subject_Lecturer subjectLecturer = new Subject_Lecturer();
            subjectLecturer.setTeachId(subjectLecturerRepo.count() + 1);
            subjectLecturer.setSubjectLecturerKey(subjectLecturerKey);
            subjectLecturer.setLecturer(lecturer);
            subjectLecturer.setSubject(subject);

            // Save the Subject_Lecturer entity to update the subject_lecturer table
            subjectLecturerRepo.save(subjectLecturer);
        }
    }

}

