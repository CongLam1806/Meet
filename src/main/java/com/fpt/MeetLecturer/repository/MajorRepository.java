package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Integer> {
    List<Major> findByStatus(boolean status);
}
