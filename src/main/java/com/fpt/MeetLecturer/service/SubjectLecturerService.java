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

//    public void updateSubjectLecturer(String Lecturerid, String Subjectid) {
//        Lecturer lecturer = lecturerRepository.findById(Lecturerid).orElse(null);
//        Subject subject = subjectRepository.findByName(Subjectid);
//
//// Check if both lecturer and subject exist
//        if (lecturer != null && subject != null) {
//            // Create a new SubjectLecturerKey instance
//            SubjectLecturerKey subjectLecturerKey = new SubjectLecturerKey(lecturer.getId(), subject.getId());
//
//            // Create a new Subject_Lecturer entity
//            Subject_Lecturer subjectLecturer = new Subject_Lecturer();
//            subjectLecturer.setId(subjectLecturerKey);
//            subjectLecturer.setLecturer(lecturer);
//            subjectLecturer.setSubject(subject);
//
//            // Save the Subject_Lecturer entity to update the subject_lecturer table
//            subjectLecturerRepo.save(subjectLecturer);
//        }
//    }
//
//
//    public void deleteSubjectLecturer(String lecturerId, int subjectId) {
//        // Retrieve the Subject_Lecturer by lecturer and subject IDs
//        Optional<Subject_Lecturer> subjectLecturerOptional = subjectLecturerRepo.findById(new SubjectLecturerKey(lecturerId, subjectId));
//
//        // Check if the Subject_Lecturer exists
//        if (subjectLecturerOptional.isPresent()) {
//            // Delete the Subject_Lecturer entity
//            subjectLecturerRepo.delete(subjectLecturerOptional.get());
//            System.out.println("Subject_Lecturer record deleted successfully.");
//        } else {
//            System.out.println("Subject_Lecturer record not found for deletion.");
//        }
//    }

}

