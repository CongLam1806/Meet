package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Slot_Subject;
import com.fpt.MeetLecturer.entity.Subject;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.mapper.MapSubject;
import com.fpt.MeetLecturer.repository.BookingRepository;
import com.fpt.MeetLecturer.repository.SlotRepository;

import com.fpt.MeetLecturer.repository.SlotSubjectRepository;
import com.fpt.MeetLecturer.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SlotService {
    @Autowired(required = false)
    private SlotRepository slotRepository;

    @Autowired(required = false)
    private SubjectRepository subjectRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired(required = false)
    private SlotSubjectRepository slotSubjectRepository;

    @Autowired(required = false)
    private MapSlot mapSlot;




    @Autowired(required = false)
    private MapSubject mapSubject;

    private static ModelMapper modelMapper = new ModelMapper();

    public ResponseDTO getSlot(){
        List<SlotDTO> slotList = mapSlot.convertListToSlotDTO(slotRepository.findAll());

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

        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS", slotList);
        return responseDTO;
    }

    public ResponseDTO getSlotBySubjectCode(String code){
        Subject subject = subjectRepository.findByCode(code);

        List<Slot> slotList = slotRepository.findAll();
        List<Slot> slots = new ArrayList<>();

        List<Slot_Subject> slotSubjects = slotSubjectRepository.findBySubjectCode(code);

        slotSubjects.forEach(slotSubject -> {
            if(slotSubject.getSubject().equals(subject)){

                slots.add(slotSubject.getSlot());
            }
        });

        return new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS BY SUBJECT CODE ", mapSlot.convertListToSlotDTO(slots));
    }

    public ResponseDTO getSlotByDate(Date startDate, Date endDate){
        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findByStartDateBetween(startDate, endDate));
        System.out.println(slotsDTO);
        return new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS BY DATE", slotsDTO);

    }

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
