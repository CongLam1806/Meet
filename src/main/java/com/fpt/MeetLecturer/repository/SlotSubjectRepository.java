package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Slot_Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotSubjectRepository extends JpaRepository<Slot_Subject, Integer> {
    Slot_Subject findBySlotId(int slotId);
}
