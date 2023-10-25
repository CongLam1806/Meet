package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Subject;
import com.fpt.MeetLecturer.entity.SubjectLecturerKey;
import com.fpt.MeetLecturer.entity.Subject_Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectLecturerRepo extends JpaRepository<Subject_Lecturer, SubjectLecturerKey> {
    @Modifying
    @Query(value = "DELETE FROM Teaching WHERE lecturerId = ?1", nativeQuery = true)
    void deleteAllByLecturerId(String id);

    @Query(value = "SELECT TOP 1 teachId FROM Teaching ORDER BY teachId DESC", nativeQuery = true)
    Long findTopTeachId();
}
