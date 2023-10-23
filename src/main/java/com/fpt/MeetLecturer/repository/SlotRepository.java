package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Slot;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Integer> {

    //List<Slot> findByStartDateBetween(Date startDate, Date endDate);

    @Query(value = "Select A.*\n" +
            "from (Slot A left join SlotSubject B on A.Id=B.slotId) full join Subject C on B.slotSubjectId=C.Id\n" +
            "where C.code = ?1 and A.meetingDay between ?2 and ?3 and A.status = 1" +
            "ORDER BY meetingDay DESC", nativeQuery = true)
    List<Slot> findBySubjectCodeAndDate(String code, Date startDate, Date endDate);

    @Query(value = "SELECT * from Slot WHERE status = 1 and meetingDay between ?1 and ?2 ORDER BY meetingDay DESC", nativeQuery = true)
    List<Slot> findByStartDateBetween(Date startDate, Date endDate);

    List<Slot> findByStatusOrderByMeetingDayDesc(boolean status);

    List<Slot> findByLecturerIdAndStatusOrderByMeetingDayDesc(String id, boolean status);

    Long countByToggle(boolean toggle);
    @Query(value = "SELECT COUNT(*) as count  FROM [dbo].[Slot]\n" +
            "WHERE YEAR(meetingDay) = ?1 AND MONTH(meetingDay) = ?2 AND [toggle] = 1", nativeQuery = true)
    Long countByToggleAndMeetingDay(int year, int month);

    Long countByLecturerIdAndToggle(String id, boolean toggle);

    @Query(value = "SELECT CONVERT(TIME, DATEADD(SECOND, SUM(DATEDIFF(SECOND, startTime, endTime)), 0)) AS TotalMeetingTime " +
            "FROM [dbo].[Slot] " +
            "WHERE [lecturerId] = ?1", nativeQuery = true)
    Time totalMeetingTime(String id);

    @Query(value = "SELECT CONVERT(TIME, DATEADD(SECOND, SUM(DATEDIFF(SECOND, startTime, endTime)), 0)) AS TotalMeetingTime " +
            "FROM [dbo].[Slot] a left join Booking b on a.Id = b.slotId " +
            "WHERE [studentId] = ?1", nativeQuery = true)
    Time totalMeetingTimeStudent(String id);

    @Query(value = "SELECT COUNT(*) as count  FROM [dbo].[Slot] " +
            "WHERE YEAR(meetingDay) = ?1 AND MONTH(meetingDay) = ?2 AND [lecturerId] = ?3 AND [toggle] = 1", nativeQuery = true)
    Long countByToggleAndMeetingDayForLecturer(int year, int month, String id);
}
