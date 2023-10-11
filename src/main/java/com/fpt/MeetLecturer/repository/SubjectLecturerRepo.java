package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.SubjectLecturerKey;
import com.fpt.MeetLecturer.entity.Subject_Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectLecturerRepo extends JpaRepository<Subject_Lecturer, SubjectLecturerKey> {
}
