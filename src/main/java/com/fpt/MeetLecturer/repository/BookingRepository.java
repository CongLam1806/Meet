package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBySlotIdAndStatus(int slotId, int status);

    Optional<Booking> findByStudentId(String id);

    Booking findByStudentEmail(String email);

    List<Booking> findByStudentIdAndSlotMeetingDayAndToggleAndStatusNot(String StudentId, LocalDate date, boolean toggle, int status);

    Booking findByStudent_EmailAndSlot_StartTimeAndSlot_EndTimeAndSlot_MeetingDayAndSlot_Mode(String studentEmail, LocalTime start, LocalTime end, LocalDate date, int mode);

    Boolean existsByStudentIdAndSlotIdAndToggle(String Id, int id, boolean toggle);

    List<Booking> findBySlotIdAndToggleAndStatus(int id, boolean toggle, int status);

    List<Booking> findAllByToggleAndStatus(boolean toggle, int status);

    List<Booking> findByToggleAndStatusAndSlotLecturerId(boolean toggle, int status, String id);

    List<Booking> findAllByStudentIdAndToggle(String studentId, boolean toggle);

    Long countByStatusAndSlotLecturerId(int status, String lecturerId);

    Long countByToggleAndStatus(boolean toggle, int status);

    Long countByStatusAndStudentId(int status, String id);

    Long countByStudentIdAndToggle(String id, boolean toggle);

    @Query(value = "SELECT COUNT(*) as count  FROM [dbo].[Booking] a left join [dbo].[Slot] b on a.slotId = b.Id " +
            "WHERE YEAR(b.meetingDay) = ?1 AND MONTH(b.meetingDay) = ?2 AND a.studentId = ?3 AND a.toggle = ?4", nativeQuery = true)
    Long countByStudentIdAndToggleMonth(int year, int month, String id, boolean toggle);

    @Query(value = "SELECT TOP 1 c.code as mostDiscuss FROM (Booking a FULL JOIN Slot b ON a.slotId = b.Id)" +
            "full join  (Subject c full join SlotSubject d on c.Id = d.subjectId) on b.Id = d.slotId\n" +
            "WHERE a.studentId = ?1 AND a.status = 2", nativeQuery = true)
    String mostDiscussSubject(String id);

    @Query(value = "SELECT TOP 1 c.code as mostDiscuss FROM (Booking a FULL JOIN Slot b ON a.slotId = b.Id)" +
            "full join  (Subject c full join SlotSubject d on c.Id = d.subjectId) on b.Id = d.slotId\n" +
            "WHERE a.studentId = ?1 AND a.status = 2 AND YEAR(b.meetingDay) = ?2 AND MONTH(b.meetingDay) = ?3", nativeQuery = true)
    String mostDiscussSubjectMonth(String id, int year, int month);

    @Query(value = "SELECT COUNT(*) as count  FROM [dbo].[Booking] a left join [dbo].[Slot] b on a.slotId = b.Id " +
            "WHERE YEAR(b.meetingDay) = ?1 AND MONTH(b.meetingDay) = ?2 AND a.studentId = ?3 AND a.status = 2", nativeQuery = true)
    Long countMeetingByDate(int year, int month, String id);
    @Query(value = "SELECT COUNT(*) as count  FROM [dbo].[Booking] a left join [dbo].[Slot] b on a.slotId = b.Id " +
            "WHERE YEAR(b.meetingDay) = ?1 AND MONTH(b.meetingDay) = ?2 AND a.status = 2", nativeQuery = true)
    Long countMeetingByDateAdmin(int year, int month);
    @Query(value = "SELECT COUNT(*) as count  FROM [dbo].[Booking] a left join [dbo].[Slot] b on a.slotId = b.Id " +
            "WHERE b.meetingDay between ?1 and ?2 AND a.studentId = ?3 AND a.status = 2", nativeQuery = true)
    Long countMeetingByWeek(LocalDate start, LocalDate end, String id);
    @Query(value = "SELECT COUNT(*) as count  FROM [dbo].[Booking] a left join [dbo].[Slot] b on a.slotId = b.Id " +
            "WHERE b.meetingDay between ?1 and ?2 AND a.status = 2", nativeQuery = true)
    Long countMeetingByWeekAdmin(LocalDate start, LocalDate end);


    @Query(value = "select b1_0.*\n" +
            "from Booking b1_0 left join Slot s1_0 on s1_0.Id=b1_0.slotId \n" +
            "where (s1_0.meetingDay > ?1 or (s1_0.startTime> ?2 and s1_0.meetingDay = ?1)) and b1_0.toggle = ?3\n" +
            "and studentId = ?4 and b1_0.status = 2 order by s1_0.meetingDay asc", nativeQuery = true)
    List<Booking> findUpComingSlot(LocalDate meetingDay, Time startTime, boolean toggle, String studentId);

    @Query(value = "select b1_0.*\n" +
            "from Booking b1_0 left join Slot s1_0 on s1_0.Id=b1_0.slotId \n" +
            "where (s1_0.meetingDay < ?1 or (s1_0.startTime < ?2 and s1_0.meetingDay = ?1)) and b1_0.toggle = ?3\n" +
            "and studentId = ?4 and b1_0.status != 0 order by s1_0.meetingDay asc", nativeQuery = true)
    List<Booking> findPastSlot(LocalDate meetingDay, Time startTime, boolean toggle, String studentId);


}
