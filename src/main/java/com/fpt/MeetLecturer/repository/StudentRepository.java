package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findByEmail(String email);

    List<Student> findByStatus(boolean status);


}
