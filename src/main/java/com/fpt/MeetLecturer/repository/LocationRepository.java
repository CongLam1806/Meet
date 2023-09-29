package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Query(value = "SELECT * FROM Location WHERE status = true", nativeQuery = true)
    List<Location> findByStatus();
    @Query(value = "SELECT * FROM Location WHERE status = false and lecturerId = 1?", nativeQuery = true)
    List<Location> findPersonalLocation(int id);

    //Location findOneByCode(int code);
}
