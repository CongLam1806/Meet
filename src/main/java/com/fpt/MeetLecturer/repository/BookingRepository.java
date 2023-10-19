package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Booking;
import org.hibernate.sql.model.internal.OptionalTableUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBySlotId(int slotId);

    Optional<Booking> findByStudentId(String id);

    Booking findByStudentEmail(String email);

    List<Booking> findBySlotIdAndToggleAndStatus(int id, boolean toggle, int status);

    List<Booking> findByToggleAndStatus(boolean toggle, int status);

    List<Booking> findAllByStudentIdAndToggle(String studentId, boolean toggle);

    Long countByStatusAndSlotLecturerId(int status,String lecturerId);

}
