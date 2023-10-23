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
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
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

            Booking booking = bookingRepository.findBySlotId(slotDTO.getId());
            if(booking != null){
                slotDTO.setStudentName(booking.getStudent().getName());
            }
            Location location = locationRepository.findById(slotDTO.getLocationId()).orElseThrow();
            slotDTO.setLocationName(location.getName());
            slotDTO.setLocationAddress(location.getAddress());

//            List<Slot_Subject> slotSubjectList = slotSubjectRepository.findBySlotId(slotDTO.getId());
//            List<Slot_SubjectDTO> slotSubjectDTOS = new ArrayList<>();
//            slotSubjectList.forEach(slotSubject -> {
//                slotSubjectDTOS.add(slotSubject.getSubject().getCode());
//            });
//            slotDTO.setSlotSubjectDTOS(subjectCode);
        });

        return  new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS", slotList);
        //return slotList;

    }

    public ResponseDTO getSlotByLecturerId(String lecturerId){
        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findByLecturerIdOrderByMeetingDayDesc(lecturerId));
        slotsDTO.forEach(slotDTO -> {

            Booking booking = bookingRepository.findBySlotId(slotDTO.getId());
            if(booking != null){
                slotDTO.setStudentName(booking.getStudent().getName());
            }
            Location location = locationRepository.findById(slotDTO.getLocationId()).orElseThrow();
            slotDTO.setLocationName(location.getName());
            slotDTO.setLocationAddress(location.getAddress());

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
        slotsDTO.forEach(slotDTO -> {
            slotDTO.setLecturerName(slotDTO.getLecturerName());
        });
        return slotsDTO;
    }

    public List<SlotDTO> getSlotBySubjectCode(String code){
        List<Slot> slots = new ArrayList<>();

        List<Slot_Subject> slotSubjects = slotSubjectRepository.findBySubjectCodeAndSlotStatusOrderBySlotMeetingDayDesc(code, true);

        slotSubjects.forEach(slotSubject -> {
            slots.add(slotSubject.getSlot());

        });
        //return new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS BY SUBJECT CODE ", mapSlot.convertListToSlotDTO(slots));
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
        slot.setPassword(slot1.getPassword());
        slot.setLecturer(slot1.getLecturer());
        slot.setLocation(slot1.getLocation());
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
        if(student != null){
            Booking booking = new Booking(slot, student);
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
//    public ResponseDTO importFromExcel(File file) {
//        try (FileInputStream fis = new FileInputStream(file);
//             Workbook workbook = new XSSFWorkbook(fis)) {
//            Sheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//            List<SlotDTO> slotdtos = new ArrayList<>();
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                if (row.getRowNum() == 0) {
//                    continue;
//                }
//                SlotDTO slot = new SlotDTO();
//                Cell idCell = row.getCell(0);
//                slot.setPassword((String) idCell.getStringCellValue());
//                Cell startTimeCell = row.getCell(1);
//                slot.setStartTime(Time.valueOf(LocalTime.parse(startTimeCell.getStringCellValue())));
//
//                Cell endTimeCell = row.getCell(2);
//                slot.setEndTime(Time.valueOf(LocalTime.parse(endTimeCell.getStringCellValue())));
//
//                Cell startDateCell = row.getCell(3);
//                slot.setMeetingDay(startDateCell.getDateCellValue());
//
//                Cell Mode = row.getCell(4);
//                slot.setMode((int) Mode.getNumericCellValue());
//
//                Cell Locations = row.getCell(5);
//                Location location = new Location();
//                int id = -1;
//                if(location.getName().equals(Locations.getStringCellValue())){
//                    id = location.getId();
//                }
//                slot.setLocationId(id);
//
//                Cell LecturerName = row.getCell(6);
//                Lecturer lecturer = new Lecturer();
//                String name = "";
//                if(lecturer.getName().equals(LecturerName.getStringCellValue())){
//                    name = lecturer.getName();
//                }
//                slot.setLecturerName(name);
//
//                Cell Subjects = row.getCell(7);
//                Subject subject = new Subject();
//                List<String> subjectList = new ArrayList<>();
//                String [] data = Subjects.getStringCellValue().split(",");
//                for(String pt: data){
//                    if(subject.getCode().equals(pt.trim().toUpperCase())){
//                        subjectList.add(subject.getName());
//                    }
//                }
//                slot.setSubjectCode(subjectList);
//
//                slotdtos.add(slot);
//            }
//            slotRepository.saveAll(mapSlot.toSlotList(slotdtos));
//        } catch (IOException e) {
//            e.getMessage();
//        }
//        return new ResponseDTO(HttpStatus.OK, "Slots added successfully!", "");
//    }
}
