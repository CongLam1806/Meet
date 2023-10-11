package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findByStatus(boolean status);


}
