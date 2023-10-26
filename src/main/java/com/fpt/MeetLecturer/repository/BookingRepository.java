package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Booking;
import org.hibernate.sql.model.internal.OptionalTableUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBySlotIdAndStatus(int slotId, int status);

    Optional<Booking> findByStudentId(String id);

    Booking findByStudentEmail(String email);

    Boolean existsByStudentIdAndSlotIdAndToggle(String Id, int id, boolean toggle);

    List<Booking> findBySlotIdAndToggleAndStatus(int id, boolean toggle, int status);

    List<Booking> findAllByToggleAndStatus(boolean toggle, int status);

    List<Booking> findByToggleAndStatusAndSlotLecturerId(boolean toggle, int status, String id);

    List<Booking> findByToggleAndStatusAndStudentId(boolean toggle, int status, String id);

    List<Booking> findAllByStudentIdAndToggle(String studentId, boolean toggle);

    Long countByStatusAndSlotLecturerId(int status,String lecturerId);
    Long countByToggleAndStatus(boolean toggle, int status);


    List<Booking> findByToggleAndStudentId(boolean toggle, String id);


    Long countByStatusAndStudentId(int status, String id);
    Long countByStudentId(String id);
    @Query(value = "SELECT TOP 1 c.code as mostDiscuss FROM (Booking a FULL JOIN Slot b ON a.slotId = b.Id)" +
            "full join  (Subject c full join SlotSubject d on c.Id = d.subjectId) on b.Id = d.slotId\n" +
            "WHERE a.studentId = ?1 AND a.status = 2", nativeQuery = true)
    String mostDiscussSubject(String id);

    @Query(value = "SELECT COUNT(*) as count  FROM [dbo].[Booking] a left join [dbo].[Slot] b on a.slotId = b.Id " +
            "WHERE YEAR(b.meetingDay) = ?1 AND MONTH(b.meetingDay) = ?2 AND a.studentId = ?3 AND a.status = 2", nativeQuery = true)
    Long countMeetingByDate(int year, int month, String id);

}
