package com.fpt.MeetLecturer.repository;





import com.fpt.MeetLecturer.entity.Lecturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
    Lecturer findByEmail(String email);

    Optional<Lecturer> findById(String id);
    List<Lecturer> findByStatus(boolean status);

//    boolean existsByUserEmail(String email);




}
