package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.Slot_SubjectDTO;
import com.fpt.MeetLecturer.business.Subject_MajorDTO;
import com.fpt.MeetLecturer.entity.*;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.mapper.MapSubject;
import com.fpt.MeetLecturer.repository.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import com.fpt.MeetLecturer.util.Utility;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
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

    @Autowired
    private LocationRepository locationRepository;

    @Autowired(required = false)
    private SlotSubjectRepository slotSubjectRepository;

    @Autowired(required = false)
    private MapSlot mapSlot;
    @Autowired(required = false)
    private Utility utility;




    @Autowired(required = false)
    private MapSubject mapSubject;

    private static ModelMapper modelMapper = new ModelMapper();

    Sort sort = Sort.by("meetingDay").descending();
    @Autowired
    private StudentRepository studentRepository;

    public ResponseDTO getAllSlot(){

        List<SlotDTO> slotList = mapSlot.convertListToSlotDTO(slotRepository.findAll(sort));
        slotList.forEach(slotDTO -> {
            System.out.println(slotDTO.getId());
            Booking booking = bookingRepository.findBySlotIdAndStatus(slotDTO.getId(), 2);
            if(booking != null){
                slotDTO.setStudentName(booking.getStudent().getName());
            }
//
//
////            List<Slot_Subject> slotSubjectList = slotSubjectRepository.findBySlotId(slotDTO.getId());
////            List<Slot_SubjectDTO> slotSubjectDTOS = new ArrayList<>();
////            slotSubjectList.forEach(slotSubject -> {
////                slotSubjectDTOS.add(slotSubject.getSubject().getCode());
////            });
////            slotDTO.setSlotSubjectDTOS(subjectCode);
        });

        return  new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS", slotList);
        //return slotList;

    }

    public ResponseDTO getSlotByLecturerId(String lecturerId){
        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findByLecturerIdAndToggleOrderByMeetingDayDesc(lecturerId, true));
        slotsDTO.forEach(slotDTO -> {
            System.out.println(slotDTO.getId());
            Booking booking = bookingRepository.findBySlotIdAndStatus(slotDTO.getId(), 2);
            if(booking != null){
                slotDTO.setStudentName(booking.getStudent().getName());
            }


//            List<Slot_Subject> slotSubjectList = slotSubjectRepository.findBySlotId(slotDTO.getId());
//            List<Slot_SubjectDTO> slotSubjectDTOS = new ArrayList<>();
//            slotSubjectList.forEach(slotSubject -> {
//                slotSubjectDTOS.add(slotSubject.getSubject().getCode());
//            });
//            slotDTO.setSlotSubjectDTOS(subjectCode);

        });

        return  new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS FOR LECTURER",slotsDTO);
        //return mapSlot.convertListToSlotDTO(slotResponse);

    }

    public List<SlotDTO> getAllSlotAvaiNow(){

        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findByStatusOrderByMeetingDayDesc(true));
        return slotsDTO;
    }

    public List<SlotDTO> getSlotBySubjectCode(String code){

//        List<Slot> slots = new ArrayList<>();
//
//        List<Slot_Subject> slotSubjects = slotSubjectRepository.findBySubjectCodeAndSlotStatusOrderBySlotMeetingDayDesc(code, true);
//
//        slotSubjects.forEach(slotSubject -> {
//            slots.add(slotSubject.getSlot());
//        });

        //return new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS BY SUBJECT CODE ", mapSlot.convertListToSlotDTO(slots));

        List<Slot> slots = slotRepository.findBySlotSubjectsSubjectCodeAndStatusOrderByMeetingDayDesc(code, true);
        return mapSlot.convertListToSlotDTO(slots);
    }

    public List<SlotDTO> getSlotByDate(Date startDate, Date endDate){
        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findByStartDateBetween(startDate, endDate));
        //return new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS BY DATE", slotsDTO);
        return slotsDTO;
    }

    public ResponseDTO getSlotByStudent(String code, Date startDate, Date endDate){
        List<SlotDTO> slotsDTOList = new ArrayList<>();
        if(code != null && startDate == null && endDate == null){
            slotsDTOList = getSlotBySubjectCode(code);
        } else if (code == null && startDate != null && endDate != null) {
            slotsDTOList = getSlotByDate(startDate, endDate);
        } else if (code != null && startDate != null && endDate != null) {
            slotsDTOList = mapSlot.convertListToSlotDTO(slotRepository.findBySubjectCodeAndDate(code, startDate, endDate));
//        } else if (code != null && startDate != null and End)
        } else if(code == null && startDate == null && endDate == null){
            slotsDTOList = getAllSlotAvaiNow();
        }
        return new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOT", slotsDTOList);

    }


    public ResponseDTO createSlot(SlotDTO newSlot){
        boolean flag = utility.checkValidTime(newSlot);

        if(!flag){
            return new ResponseDTO(HttpStatus.OK,
                    "New slot start time must after existing slot end time at least 15 minutes", "error");
        }


        Slot slot1 = modelMapper.map(newSlot, Slot.class);
        Slot slot = new Slot();
        if(slot1.getMode() == 0 || slot1.getMode() == 1){
            slot.setStatus(true);
        } else if (slot1.getMode() == 2){
            slot.setStatus(false);
        }
        slot.setPassword(slot1.getPassword());
        slot.setLecturer(slot1.getLecturer());
        if(!slot1.isOnline()){
            slot.setLocation(slot1.getLocation());
            slot.setOnline(false);
        }

        slot.setStartTime(slot1.getStartTime());
        slot.setEndTime(slot1.getEndTime());
        slot.setMeetingDay(slot1.getMeetingDay());
        slot.setMode(slot1.getMode());

//        Booking booking = bookingRepository.findBySlotId(slotDTO.getId());
//        if(booking != null){
//            slotDTO.setStudentName(booking.getStudent().getName());
//        }

        slot = slotRepository.save(slot);
        Student student = studentRepository.findByEmail(newSlot.getStudentEmail());
        Booking booking;
        if(student != null){
            booking = new Booking(slot, student, 2);
            bookingRepository.save(booking);
        }



        for (Slot_SubjectDTO slotSubjectDTO : newSlot.getSlotSubjectDTOS()){
            Subject subject = subjectRepository.findByCode(slotSubjectDTO.getSubjectCode());
            Slot_Subject slotSubject = new Slot_Subject(slot, subject);
            slotSubjectRepository.save(slotSubject);
        }

        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "CREATE SLOT SUCCESSFULLY", mapSlot.convertSlotToSlotDTO(slot));
        return responseDTO;
    }


    public ResponseDTO updateSlot(SlotDTO newSlot, int id){
        boolean flag = utility.checkValidTime(newSlot);
//        if(!flag) {
//            return new ResponseDTO(HttpStatus.OK,
//                    "Slot start time must after existing slot end time at least 15 minutes", "error");
//        }
        Slot slot;
        slot = slotRepository.findById(id).orElseThrow();
        modelMapper.map(newSlot, slot);
        slotRepository.save(slot);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "UPDATE SLOT SUCCESSFULLY", mapSlot.convertSlotToSlotDTO(slot));
        return responseDTO;
    }

    public ResponseDTO deleteSlot(int id) {
        Slot slot = slotRepository.findById(id).orElseThrow();
        if(slot.isToggle()){
            slot.setToggle(false);
            slot.setStatus(false);
            slotRepository.save(slot);
        }
        return new ResponseDTO(HttpStatus.OK, "DELETE SLOT SUCCESSFULLY", "");
    }
    public ResponseDTO importFromExcel(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                // khởi tạo slotDTO để lấy gia trị từ file excel
                SlotDTO slotdto = new SlotDTO();
                //MeetingDay
                Cell meetingDay = row.getCell(0);
                slotdto.setMeetingDay(LocalDate.from(meetingDay.getLocalDateTimeCellValue()));
                //StartTime
                Cell startTime = row.getCell(1);
                slotdto.setStartTime(Time.valueOf(LocalTime.parse(startTime.getStringCellValue())));
                //EndTime
                Cell endTime = row.getCell(2);
                slotdto.setEndTime(Time.valueOf(LocalTime.parse(endTime.getStringCellValue())));
                //LocationId
                Cell locationId = row.getCell(3);
                Location location = new Location();
                int id = -1;
                if (location.getId() == locationId.getNumericCellValue()) {
                    id = location.getId();
                }
                slotdto.setLocationId(id);
                //SubjectList
                Cell subjects = row.getCell(4);
                List<Slot_SubjectDTO> subjectList = new ArrayList<>();
                String[] data = subjects.getStringCellValue().split(",");
                for (String pt : data) {
                    Subject subject = subjectRepository.findByCode(pt.trim());
                    if (subject.getCode() != null) {
                        Slot_SubjectDTO slotSubjectDTO = new Slot_SubjectDTO();
                        slotSubjectDTO.setSubjectCode(subject.getCode());
                        subjectList.add(slotSubjectDTO);
                    }
                }
                slotdto.setSlotSubjectDTOS(subjectList);
                //SlotMode
                Cell mode = row.getCell(5);
                slotdto.setMode((int) mode.getNumericCellValue());
                //StudentName
                Cell studentEmail = row.getCell(6);
                Student student = studentRepository.findByEmail(studentEmail.getStringCellValue());
                if (!student.getEmail().isBlank()) {
                    slotdto.setStudentEmail(student.getEmail());
                }
                //SlotPassword
                Cell password = row.getCell(7);
                if (password != null) slotdto.setPassword(password.getStringCellValue());
                //Map DTO vào Entity, tiến hành lưu thông tin vào DB
                Slot slot1 = modelMapper.map(slotdto, Slot.class);
                Slot slot = new Slot();
                slot.setPassword(slot1.getPassword());
                slot.setLecturer(slot1.getLecturer());
                slot.setLocation(slot1.getLocation());
                slot.setStartTime(slot1.getStartTime());
                slot.setEndTime(slot1.getEndTime());
                slot.setMeetingDay(slot1.getMeetingDay());
                slot.setMode(slot1.getMode());
                slot = slotRepository.save(slot);
                if (student != null) {
                    Booking booking = new Booking(slot, student, 2);
                    bookingRepository.save(booking);
                }
                for (Slot_SubjectDTO slotSubjectDTO : slotdto.getSlotSubjectDTOS()) {
                    Subject subject = subjectRepository.findByCode(slotSubjectDTO.getSubjectCode());
                    Slot_Subject slotSubject = new Slot_Subject(slot, subject);
                    slotSubjectRepository.save(slotSubject);
                }
            }
        } catch (IOException e) {
            String errorMessage = e.getMessage();
            return new ResponseDTO(HttpStatus.OK,
                    "Error occurred during file processing", errorMessage);
        }
        return new ResponseDTO(HttpStatus.OK, "Slots added successfully!", "");
    }
}
