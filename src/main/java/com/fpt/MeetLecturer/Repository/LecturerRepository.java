package com.fpt.MeetLecturer.Repository;


import com.fpt.MeetLecturer.EntityModel.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    Lecturer findByUserEmail(String email);
}
