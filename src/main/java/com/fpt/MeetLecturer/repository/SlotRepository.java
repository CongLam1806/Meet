package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Integer> {

    //List<Slot> findByStartDateBetween(Date startDate, Date endDate);

    @Query(value = "SELECT * from Slot WHERE meetingDay between ?1 and ?2", nativeQuery = true)
    List<Slot> findByStartDateBetween(Date startDate, Date endDate);
}
