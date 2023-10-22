package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query(value = "SELECT * FROM Location WHERE status = 1", nativeQuery = true)
    List<Location> findPublicLocation();
    @Query(value = "SELECT * FROM Location WHERE lecturerId = ?1 or status = 1", nativeQuery = true)
    List<Location> findPersonalLocation(String id);

    List<Location> findByLecturerId(String id);

    Long countByLecturerId(String id);
}
