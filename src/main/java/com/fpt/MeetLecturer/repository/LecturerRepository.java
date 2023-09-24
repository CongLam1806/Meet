package com.fpt.MeetLecturer.repository;


import com.fpt.MeetLecturer.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    Lecturer findByUserEmail(String email);
}
