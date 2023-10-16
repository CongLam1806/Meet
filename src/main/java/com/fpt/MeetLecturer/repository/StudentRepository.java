package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByStudentEmail(String email);

    List<Student> findByStudentStatus(boolean status);

    Student findByStudentId(String id);
}
