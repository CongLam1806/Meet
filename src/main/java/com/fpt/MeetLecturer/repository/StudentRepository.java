package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    //List<Student> findByEmail(String email);

    List<Student> findByStatus(boolean status);

    Student findByEmail(String email);

    Optional<Student> findById(String id);

    Long countByStatus(boolean status);
}
