package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Integer> {

    @Query(value = "SELECT u from Slot u  where u.meeting_date between ?1 and ?2", nativeQuery = true)
    List<Slot> findByStartDateBetween(Date startDate, Date endDate);
}
