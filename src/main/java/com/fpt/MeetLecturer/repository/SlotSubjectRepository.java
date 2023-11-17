package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.SlotSubjectKey;
import com.fpt.MeetLecturer.entity.Slot_Subject;
import com.fpt.MeetLecturer.entity.Subject;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SlotSubjectRepository extends JpaRepository<Slot_Subject, SlotSubjectKey> {
    List<Slot_Subject> findBySlotId(int slotId);

    List<Slot_Subject> findBySubjectCodeAndSlotStatusOrderBySlotMeetingDayDesc(String code, boolean status);

    List<Slot_Subject> findBySubjectId(int subjectId);

    @Modifying
    @Query(value = "DELETE FROM SlotSubject WHERE slotId = ?1", nativeQuery = true)
    void deleteBySlotId(int id);
}
