package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.SubjectMajorKey;
import com.fpt.MeetLecturer.entity.Subject_Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface SubjectMajorRepo extends JpaRepository<Subject_Major, SubjectMajorKey> {

    @Modifying
    @Query(value = "Delete from Subject_Major Where subjectId = ?1", nativeQuery = true)
    void deleteAllBySubjectId(int id);

}