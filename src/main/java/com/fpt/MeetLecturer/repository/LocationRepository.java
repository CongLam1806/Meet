package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query(value = "SELECT * FROM Location WHERE status = 1 AND toggle = 1", nativeQuery = true)
    List<Location> findPublicLocation();
    List<Location> findByLecturerIdAndToggleAndStatus(String id, boolean toggle, boolean status);
    @Query(value = "SELECT * FROM Location WHERE status = 1 OR toggle = 1 AND lecturerId = ?1", nativeQuery = true)
    List<Location> findByLecturerIdAndToggleAndStatus(String id);
    List<Location> findByToggle(boolean toggle);
    Long countByLecturerIdAndToggle(String id, boolean toggle);
}
