package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.*;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.mapper.MapSubject;
import com.fpt.MeetLecturer.repository.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SlotService {
    @Autowired(required = false)
    private SlotRepository slotRepository;

    @Autowired(required = false)
    private SubjectRepository subjectRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired(required = false)
    private SlotSubjectRepository slotSubjectRepository;

    @Autowired(required = false)
    private MapSlot mapSlot;




    @Autowired(required = false)
    private MapSubject mapSubject;

    private static ModelMapper modelMapper = new ModelMapper();

    Sort sort = Sort.by("meetingDay").descending();

    public List<SlotDTO> getAllSlot(){

        List<SlotDTO> slotList = mapSlot.convertListToSlotDTO(slotRepository.findAll(sort));

        slotList.forEach(slotDTO -> {

            Booking booking = bookingRepository.findBySlotId(slotDTO.getId());
            if(booking != null){
                slotDTO.setStudentEmail(booking.getStudent().getEmail());
            }

            List<Slot_Subject> slotSubjectList = slotSubjectRepository.findBySlotId(slotDTO.getId());
            List<String> subjectCode = new ArrayList<>();
            slotSubjectList.forEach(slotSubject -> {
                subjectCode.add(slotSubject.getSubject().getCode());
            });
            slotDTO.setSubjectCode(subjectCode);
        });

        //return  new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS", slotList);
        return slotList;

    }

    public List<SlotDTO> getSlotByLecturerId(String lecturerId){
        List<Slot> slotList =slotRepository.findAll(sort);
        List<Slot> slotResponse = new ArrayList<>();

        Lecturer lecturer = lecturerRepository.findById(lecturerId).orElseThrow();
        slotList.forEach(slot -> {
            if(slot.getLecturer().getId().equals(lecturer.getId())){
                slotResponse.add(slot);
            }
        });

        //return  new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS", mapSlot.convertListToSlotDTO(slotResponse));
        return mapSlot.convertListToSlotDTO(slotResponse);

    }

    public List<SlotDTO> getSlotBySubjectCode(String code){
        Subject subject = subjectRepository.findByCode(code);
        List<Slot> slots = new ArrayList<>();

        List<Slot_Subject> slotSubjects = slotSubjectRepository.findBySubjectCodeOrderBySlotMeetingDayDesc(code);

        slotSubjects.forEach(slotSubject -> {
            if(slotSubject.getSubject().equals(subject)){
                slots.add(slotSubject.getSlot());
            }
        });
        //return new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS BY SUBJECT CODE ", mapSlot.convertListToSlotDTO(slots));
        return mapSlot.convertListToSlotDTO(slots);
    }

    public List<SlotDTO> getSlotByDate(Date startDate, Date endDate){
        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findByStartDateBetween(startDate, endDate));
        slotsDTO.forEach(slotDTO -> {
            System.out.println(slotDTO.getMeetingDay());
            System.out.println("OK");
        });

        //return new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS BY DATE", slotsDTO);
        return slotsDTO;
    }

//    public ResponseDTO getSlotByStudent(String code, Date startDate, Date endDate){
//        List<SlotDTO> slotsDTOList = new ArrayList<>();
//        if(code != null && startDate == null && endDate == null){
//            slotsDTOList = getSlotBySubjectCode(code);
//        } else if (code == null && startDate != null && endDate != null) {
//            slotsDTOList = getSlotByDate(startDate, endDate);
//        } else if (code == null && startDate == null && endDate == null){
//
//        }
//
//    }

    public ResponseDTO createSlot(SlotDTO newSlot){
        Slot slot = new Slot();
        modelMapper.map(newSlot, slot);
        slotRepository.save(slot);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "CREATE SLOT SUCCESSFULLY", mapSlot.convertSlotToSlotDTO(slot));
        return responseDTO;
    }

    public ResponseDTO updateSlot(SlotDTO newSlot){
        Slot slot;
        slot = slotRepository.findById(newSlot.getId()).orElseThrow();
        modelMapper.map(newSlot, slot);
        slotRepository.save(slot);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "UPDATE SLOT SUCCESSFULLY", mapSlot.convertSlotToSlotDTO(slot));
        return responseDTO;
    }

    public ResponseDTO deleteSlot(int id) {
        boolean bool;
        Slot slot = slotRepository.findById(id).orElseThrow();
        if (slot.getId() == 0) {
            bool = false;
        } else {
            //User delUser = user1.get();
            //userRepository.delete(user1.get());
            if (!slot.isStatus()) {
                bool = false;
            }
            slot.setStatus(false);
            slotRepository.save(slot);
            //mapUser.mapUserToUserDTO(delUser);
            bool = true;
        }
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "DELETE SLOT SUCCESSFULLY", bool);
        return responseDTO;
    }
}
