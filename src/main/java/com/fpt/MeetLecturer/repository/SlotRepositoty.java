package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Location;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SlotRepositoty extends JpaRepository<Slot,Integer> {
    //List<SlotDTO> slotsDTO =
    List<Slot> findByLikedSubjectsId(String id);

    //List<Slot> findByStartDateBetween(Date startDate, Date endDate);

    @Query(value = "SELECT u from Slot u where u.meetingDate between ?1 and ?2", nativeQuery = true)
    List<Slot> findByStartDateBetween(Date startDate, Date endDate);
}
