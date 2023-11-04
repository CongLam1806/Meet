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
    List<Location> findByLecturerIdAndToggle(String id, boolean toggle);
    @Query(value = "SELECT * FROM Location WHERE address is not null and status = 1 and toggle = 1 OR toggle = 1 AND address is not null and lecturerId  = ?", nativeQuery = true)
    List<Location> findByLecturerIdAndToggleAndStatus(String id);
    List<Location> findByToggle(boolean toggle);
    Long countByLecturerIdAndToggle(String id, boolean toggle);
}
