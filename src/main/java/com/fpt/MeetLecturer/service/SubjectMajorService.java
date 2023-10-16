package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.entity.*;
import com.fpt.MeetLecturer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectMajorService {

    @Autowired
    private SubjectMajorRepo subjectMajorRepo;
    @Autowired
    private MajorRepository majorRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public void updateSubject_Major(int Subjectid, int majorId) {
        Major major = majorRepository.findById(majorId).orElse(null);
        Subject subject = subjectRepository.findById(Subjectid).orElse(null);

// Check if both lecturer and subject exist
        if (subject != null && major != null) {
            SubjectMajorKey subjectMajorKey = new SubjectMajorKey(major.getId(), subject.getId());

            // Create a new Subject_Lecturer entity
            Subject_Major subjectMajor = new Subject_Major();
            subjectMajor.setSubject_majorId(subjectMajorRepo.count() + 1);
            subjectMajor.setSubjectMajorKey(subjectMajorKey);
            subjectMajor.setMajor(major);
            subjectMajor.setSubject(subject);

            // Save the Subject_Lecturer entity to update the subject_lecturer table
            subjectMajorRepo.save(subjectMajor);
        }
    }

}
