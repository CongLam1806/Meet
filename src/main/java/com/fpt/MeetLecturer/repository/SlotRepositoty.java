package com.fpt.MeetLecturer.repository;

import com.fpt.MeetLecturer.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepositoty extends JpaRepository<Slot,Integer> {
}
