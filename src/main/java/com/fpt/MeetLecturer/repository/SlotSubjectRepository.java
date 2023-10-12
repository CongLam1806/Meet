package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Slot_Subject;
import com.fpt.MeetLecturer.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotSubjectRepository extends JpaRepository<Slot_Subject, Integer> {
    List<Slot_Subject> findBySlotId(int slotId);

    List<Slot_Subject> findBySubjectCode(String code);
}
