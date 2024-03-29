package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Slot;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Integer> {

    //List<Slot> findByStartDateBetween(Date startDate, Date endDate);

    @Query(value = "Select A.*\n" +
            "from (Slot A left join SlotSubject B on A.Id=B.slotId) full join Subject C on B.subjectId=C.Id\n" +
            "where C.code = ?1 and A.meetingDay between ?2 and ?3 and A.status = 1" +
            "ORDER BY meetingDay DESC", nativeQuery = true)
    List<Slot> findBySubjectCodeAndDate(String code, Date startDate, Date endDate);

    @Query (value = "Select A.*\n" +
            "            from (Slot A left join SlotSubject B on A.Id=B.slotId) \n" +
            "\t\t\tfull join Lecturer L on L.Id = A.lecturerId\n" +
            "\t\t\tfull join Subject C on B.subjectId=C.Id\n" +
            "            where C.code = ?1 and A.meetingDay between ?2 and ?3 and A.status = 1 and L.email=?4\n" +
            "            ORDER BY meetingDay DESC", nativeQuery = true)
    List<Slot> findBySubjectCodeAndDateAndLecturerEmail(String code, Date startDate, Date endDate, String lecturerEmail);


    @Query(value = "SELECT * from Slot WHERE status = 1 and meetingDay between ?1 and ?2 ORDER BY meetingDay DESC", nativeQuery = true)
    List<Slot> findByStartDateBetween(Date startDate, Date endDate);

    @Query(value = "SELECT s.* \n" +
            "FROM Slot s\n" +
            "LEFT JOIN Lecturer l ON s.lecturerId = l.id \n" +
            "WHERE s.status = 1\n" +
            "  AND s.meetingDay BETWEEN ?1 AND ?2\n" +
            "  AND l.email = ?3\n" +
            "ORDER BY s.meetingDay DESC;\n", nativeQuery = true)
    List<Slot> findByStartDateBetweenAndLectureEmail(Date startDate, Date endDate, String lecturerEmail);

    List<Slot> findByStatusOrderByMeetingDayDesc(boolean status);

    List<Slot> findSlotByLecturerEmailAndStatusOrderByMeetingDayDesc(String email, boolean status);


    List<Slot> findByLecturerIdAndToggleOrderByMeetingDayDesc(String id, boolean toggle);

    List<Slot> findByLecturerIdOrderByMeetingDayDesc(String id);

    List<Slot> findBySlotSubjectsSubjectCodeAndStatusOrderByMeetingDayDesc(String code, boolean status);
    List<Slot> findByLecturerEmailAndSlotSubjectsSubjectCodeAndStatusOrderByMeetingDayDesc(String lecturerEmail, String code, boolean status);


    List<Slot> findByLecturerIdAndStatusOrderByMeetingDayDesc(String id, boolean status);
    //Admin:
    @Query(value = "SELECT CONVERT(TIME, DATEADD(SECOND, SUM(DATEDIFF(SECOND, startTime, endTime)), 0)) AS TotalMeetingTime " +
            "FROM [dbo].[Slot] a left join Booking b on a.Id = b.slotId " +
            "WHERE a.status = 0 AND YEAR(meetingDay) = ?1 AND MONTH(meetingDay) = ?2 AND b.status = 2 ", nativeQuery = true)
    Time totalMeetingTimeAdminMonth(int year, int month);
    @Query(value = "SELECT CONVERT(TIME, DATEADD(SECOND, SUM(DATEDIFF(SECOND, startTime, endTime)), 0)) AS TotalMeetingTime " +
            "FROM [dbo].[Slot] a left join Booking b on a.Id = b.slotId " +
            "WHERE a.status = 0 AND meetingDay between ?1 and ?2 AND b.status = 2 ", nativeQuery = true)
    Time totalMeetingTimeAdmin(LocalDate start, LocalDate end);
    @Query(value = "SELECT COUNT(*) FROM [dbo].[Slot] " +
            "WHERE meetingDay between ?1 and ?2 AND toggle = 1", nativeQuery = true)
    Long countByWeekForAdmin(LocalDate start, LocalDate end);
    Long countByToggle(boolean toggle);
    @Query(value = "SELECT COUNT(*) as count  FROM [dbo].[Slot]\n" +
            "WHERE YEAR(meetingDay) = ?1 AND MONTH(meetingDay) = ?2 AND [toggle] = 1", nativeQuery = true)
    Long countByToggleAndMeetingDay(int year, int month);
    //Lecturer:
    Long countByLecturerIdAndToggle(String id, boolean toggle);

    @Query(value = "SELECT CONVERT(TIME, DATEADD(SECOND, SUM(DATEDIFF(SECOND, a.startTime, a.endTime)), 0)) AS TotalMeetingTime " +
            "FROM [dbo].[Slot] a left join Booking b on a.Id = b.slotId " +
            "WHERE a.lecturerId = ?1 AND a.status = 0 AND b.status = 2", nativeQuery = true)//AND a.status = 0 AND b.status = 2 AND YEAR(a.meetingDay) between 2020 and 2030
    Time totalMeetingTime(String id);

    @Query(value = "SELECT CONVERT(TIME, DATEADD(SECOND, SUM(DATEDIFF(SECOND, startTime, endTime)), 0)) AS TotalMeetingTime " +
            "FROM [dbo].[Slot] a left join Booking b on a.Id = b.slotId " +
            "WHERE a.lecturerId = ?1 AND a.status = 0 AND YEAR(a.meetingDay) = ?2 AND MONTH(a.meetingDay) = ?3 AND b.status = 2", nativeQuery = true)
    Time totalMeetingTimeMonth(String id, int year, int month);

    @Query(value = "SELECT COUNT(*) as count  FROM [dbo].[Slot] " +
            "WHERE YEAR(meetingDay) = ?1 AND MONTH(meetingDay) = ?2 AND [lecturerId] = ?3 AND [toggle] = 1", nativeQuery = true)
    Long countByToggleAndMeetingDayForLecturer(int year, int month, String id);

    @Query(value = "SELECT COUNT(*) FROM [dbo].[Slot] " +
    "WHERE meetingDay between ?1 and ?2 AND toggle = 1 AND [lecturerId] = ?3", nativeQuery = true)
    Long countByWeekForLecturer(LocalDate start, LocalDate end, String id);


    @Query(value = "SELECT TOP 1 d.code\n" +
            "FROM Slot a\n" +
            "FULL JOIN Booking b ON a.Id = b.slotId\n" +
            "LEFT JOIN SlotSubject c ON a.Id = c.slotId\n" +
            "FULL JOIN Subject d ON d.Id = c.subjectId\n" +
            "WHERE a.lecturerId = ?1 AND a.status = 0 AND b.status = 2\n" +
            "GROUP BY d.code\n" +
            "ORDER BY COUNT(*) DESC", nativeQuery = true)
    String mostDiscussSubjectLecturer(String id);

    @Query(value = "SELECT TOP 1 d.code as mostDiscuss FROM (Slot a FULL JOIN Booking b ON a.Id = b.slotId) left JOIN  (SlotSubject c full join Subject d on d.Id = c.subjectId) on a.Id = c.slotId\n" +
            "WHERE a.lecturerId = ?1 AND a.status = 0 AND b.status = 2 AND YEAR(meetingDay) = ?2 AND MONTH(meetingDay) = ?3 GROUP BY d.code ORDER BY COUNT(*) DESC", nativeQuery = true)
    String mostDiscussSubjectLecturerMonth(String id, int year, int month);



    //Student:
    @Query(value = "SELECT CONVERT(TIME, DATEADD(SECOND, SUM(DATEDIFF(SECOND, startTime, endTime)), 0)) AS TotalMeetingTime " +
            "FROM [dbo].[Slot] a left join Booking b on a.Id = b.slotId " +
            "WHERE [studentId] = ?1 AND b.status = 2", nativeQuery = true)
    Time totalMeetingTimeStudent(String id);
    @Query(value = "SELECT CONVERT(TIME, DATEADD(SECOND, SUM(DATEDIFF(SECOND, startTime, endTime)), 0)) AS TotalMeetingTime " +
            "FROM [dbo].[Slot] a left join Booking b on a.Id = b.slotId " +
            "WHERE [studentId] = ?1 AND b.status = 2 AND YEAR(meetingDay) = ?2 AND MONTH(meetingDay) = ?3", nativeQuery = true)
    Time totalMeetingTimeStudentMonth(String id, int year, int month);


}
