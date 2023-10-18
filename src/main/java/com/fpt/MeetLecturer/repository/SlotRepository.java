package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Slot;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Integer> {

    //List<Slot> findByStartDateBetween(Date startDate, Date endDate);

    @Query(value = "Select A.*\n" +
            "from (Slot A left join SlotSubject B on A.Id=B.slotId) full join Subject C on B.slotSubjectId=C.Id\n" +
            "where A.Id = ?1 and A.meetingDay between ?2 and ?3 " +
            "ORDER BY meetingDay DESC", nativeQuery = true)
    List<Slot> findBySubjectCodeAndDate(String code, Date startDate, Date endDate);

    @Query(value = "SELECT * from Slot WHERE meetingDay between ?1 and ?2 ORDER BY meetingDay DESC", nativeQuery = true)
    List<Slot> findByStartDateBetween(Date startDate, Date endDate);

    List<Slot> findByStatus(boolean status);

    List<Slot> findByLecturerIdOrderByMeetingDayDesc(String id);

    List<Slot> findByMeetingDayIsGreaterThanOrStartTimeIsGreaterThanAndStatus(Date meetingDay, Time startTime, boolean status);
}
