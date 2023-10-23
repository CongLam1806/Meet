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
    Booking findBySlotId(int slotId);

    Optional<Booking> findByStudentId(String id);

    Booking findByStudentEmail(String email);

    Boolean existsByStudentIdAndSlotId(String Id, int id);

    List<Booking> findBySlotIdAndToggleAndStatus(int id, boolean toggle, int status);

    List<Booking> findByToggleAndStatusAndSlotLecturerId(boolean toggle, int status, String id);

    List<Booking> findAllByStudentIdAndToggle(String studentId, boolean toggle);

    Long countByStatusAndSlotLecturerId(int status,String lecturerId);
    Long countByToggleAndStatus(boolean toggle, int status);

    Long countByStatusAndStudentId(int status, String id);
    Long countByStudentId(String id);
    @Query(value = "SELECT TOP 1 c.code as mostDiscuss FROM (Booking a FULL JOIN Slot b ON a.slotId = b.Id)" +
            "full join  (Subject c full join SlotSubject d on c.Id = d.subjectId) on b.Id = d.slotId\n" +
            "WHERE a.studentId = ?1", nativeQuery = true)
    String mostDiscussSubject(String id);

}
