package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.repository.LecturerRepository;
import com.fpt.MeetLecturer.repository.SlotRepository;
import com.fpt.MeetLecturer.repository.SlotSubjectRepository;
import com.fpt.MeetLecturer.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SubjectSlotService {
    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SlotSubjectRepository slotSubjectRepository;


}
