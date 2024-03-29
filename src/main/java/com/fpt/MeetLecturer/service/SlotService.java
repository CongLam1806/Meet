package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.ExcelDataDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.business.Slot_SubjectDTO;
import com.fpt.MeetLecturer.entity.*;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.mapper.MapSubject;
import com.fpt.MeetLecturer.repository.*;
import com.fpt.MeetLecturer.util.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SlotService {
    @Autowired(required = false)
    private SlotRepository slotRepository;

    @Autowired(required = false)
    private SubjectRepository subjectRepository;

    @Autowired
    private LecturerRepository lecturerRepository;


    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private EmailSenderService emailSenderService;

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
        });
        return  new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS", slotList);
    }

    public ResponseDTO getSlotByLecturerId(String lecturerId){
        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findByLecturerIdAndToggleOrderByMeetingDayDesc(lecturerId, true));
        slotsDTO.forEach(slotDTO -> {
            System.out.println(slotDTO.getId());
            Booking booking = bookingRepository.findBySlotIdAndStatus(slotDTO.getId(), 2);

            if(booking != null){
                slotDTO.setStudentName(booking.getStudent().getName());
                slotDTO.setStudentEmail(booking.getStudent().getEmail());
                slotDTO.setStudentPhone(booking.getStudent().getPhone());
                Subject subjectBooked = booking.getSubject();
                if(subjectBooked != null){
                    slotDTO.setSubject(subjectBooked.getCode());
                }



            }



        });
        return  new ResponseDTO(HttpStatus.OK, "FOUND ALL SLOTS FOR LECTURER",slotsDTO);
    }

    public List<SlotDTO> getAllSlotAvaiNow(){

        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findByStatusOrderByMeetingDayDesc(true));
        return slotsDTO;
    }

    public List<SlotDTO> getSlotBySubjectCode(String code){
        List<Slot> slots = slotRepository.findBySlotSubjectsSubjectCodeAndStatusOrderByMeetingDayDesc(code, true);
        return mapSlot.convertListToSlotDTO(slots);
    }

    public List<SlotDTO> getSlotByLecturerCode(String lecturerCode){
        String lecturerEmail = lecturerRepository.findByEmailContains(lecturerCode).getEmail();
        List<Slot> slots = slotRepository.findSlotByLecturerEmailAndStatusOrderByMeetingDayDesc(lecturerEmail, true);
        return mapSlot.convertListToSlotDTO(slots);
    }

    public List<SlotDTO> getSlotByLecturerCodeAndSubjectCode(String code, String lecturerCode){
        String lecturerEmail = lecturerRepository.findByEmailContains(lecturerCode).getEmail();
        List<Slot> slots = slotRepository.findByLecturerEmailAndSlotSubjectsSubjectCodeAndStatusOrderByMeetingDayDesc(lecturerEmail, code, true);
        return mapSlot.convertListToSlotDTO(slots);
    }



    public List<SlotDTO> getSlotByDate(Date startDate, Date endDate){
        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findByStartDateBetween(startDate, endDate));
        return slotsDTO;
    }

    public List<SlotDTO> getSlotByDateAndLecturerCode(Date startDate, Date endDate, String lecturerCode){
        String lecturerEmail = lecturerRepository.findByEmailContains(lecturerCode).getEmail();
        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findByStartDateBetweenAndLectureEmail(startDate, endDate, lecturerEmail));
        return slotsDTO;
    }
    public List<SlotDTO> getSlotBySubjectCodeAndDateAndLecturerEmail(String code, Date startDate, Date endDate, String lecturerCode){
        String lecturerEmail = lecturerRepository.findByEmailContains(lecturerCode).getEmail();
        List<SlotDTO> slotsDTO = mapSlot.convertListToSlotDTO(slotRepository.findBySubjectCodeAndDateAndLecturerEmail(code, startDate, endDate, lecturerEmail));
        return slotsDTO;
    }

    public ResponseDTO getSlotByStudent(String code, String lecturerCode, Date startDate, Date endDate){
        List<SlotDTO> slotsDTOList = new ArrayList<>();
        if(code != null && lecturerCode == null && startDate == null && endDate == null){
            slotsDTOList = getSlotBySubjectCode(code);
        } else if (code != null && lecturerCode != null && startDate == null && endDate == null) {
            slotsDTOList = getSlotByLecturerCodeAndSubjectCode(code, lecturerCode);
        } else if (code == null && lecturerCode == null && startDate != null && endDate != null) {
            slotsDTOList = getSlotByDate(startDate, endDate);
        } else if (code == null && lecturerCode != null && startDate != null && endDate != null) {
            slotsDTOList = getSlotByDateAndLecturerCode(startDate, endDate, lecturerCode);
        } else if (code != null && startDate != null && endDate != null && lecturerCode == null) {
            slotsDTOList = mapSlot.convertListToSlotDTO(slotRepository.findBySubjectCodeAndDate(code, startDate, endDate));
        } else if (code != null && startDate != null && endDate != null && lecturerCode != null) {
            slotsDTOList = getSlotBySubjectCodeAndDateAndLecturerEmail(code, startDate, endDate, lecturerCode);
//        } else if (code != null && startDate != null and End)
        } else if (code == null && startDate == null && endDate == null && lecturerCode != null) {
            slotsDTOList = getSlotByLecturerCode(lecturerCode);
        } else if(code == null && startDate == null && endDate == null && lecturerCode == null){
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

        Slot slot1 = mapSlot.convertSlotDTOToSlot(newSlot);
        Slot slot = new Slot();
        if(slot1.getMode() == 0 || slot1.getMode() == 1){
            slot.setStatus(true);
        } else if (slot1.getMode() == 2){
            slot.setStatus(false);
        }
        slot.setPassword(slot1.getPassword());
        slot.setLecturer(slot1.getLecturer());
        slot.setOnline(slot1.isOnline());
        slot.setStartTime(slot1.getStartTime());
        slot.setEndTime(slot1.getEndTime());
        slot.setMeetingDay(slot1.getMeetingDay());
        slot.setMode(slot1.getMode());
        if(slot1.isOnline()){
            slot.setLocation(null);
        } else {
            slot.setLocation(slot1.getLocation());
        }
        slot = slotRepository.save(slot);

        SlotDTO slotResponseDTO = mapSlot.convertSlotToSlotDTO(slot);

        Student student = studentRepository.findByEmail(newSlot.getStudentEmail());


        for (Slot_SubjectDTO slotSubjectDTO : newSlot.getSlotSubjectDTOS()){
            Subject subject = subjectRepository.findByCode(slotSubjectDTO.getSubjectCode());
            if(student != null){
                Booking booking = new Booking(slot, student, subject, 2);
                Booking s = bookingRepository.save(booking);
//            emailSenderService.sendHtmlEmail(student.getEmail(), s, 3);
            }
            Slot_Subject slotSubject = new Slot_Subject(slot, subject);
            slotSubjectRepository.save(slotSubject);
        }

        if (newSlot.getMode() == 2){
            Booking booking = bookingRepository.
                    findByStudent_EmailAndSlot_StartTimeAndSlot_EndTimeAndSlot_MeetingDayAndSlot_Mode
                            (newSlot.getStudentEmail(), slot1.getStartTime(), slot1.getEndTime(), slot1.getMeetingDay(), 2);
            emailSenderService.sendHtmlEmail(newSlot.getStudentEmail(), booking, 3, booking.getSlot().isOnline());
        }

        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "CREATE SLOT SUCCESSFULLY", "");
        return responseDTO;
    }


    public ResponseDTO updateSlot(SlotDTO newSlot, int id){
//        boolean flag = utility.checkValidTime(newSlot);
//        if(!flag) {
//            return new ResponseDTO(HttpStatus.OK,
//                    "Slot start time must after existing slot end time at least 15 minutes", "error");
//        }
        Slot slot;
        slot = slotRepository.findById(id).orElseThrow();
        System.out.println(slot.getId());
        slot.setOnline(newSlot.isOnline());
        if(!slot.isOnline()){
            Location location = locationRepository.findById(newSlot.getLocationId()).orElseThrow();
            slot.setLocation(location);
        } else {
            slot.setLocation(null);
        }
//        slotSubjectRepository.deleteBySlotId(newSlot.getId());
//
//        for (Slot_SubjectDTO slotSubjectDTO : newSlot.getSlotSubjectDTOS()){
//            Subject subject = subjectRepository.findByCode(slotSubjectDTO.getSubjectCode());
//
//            Slot_Subject slotSubject = new Slot_Subject(slot, subject);
//            slotSubjectRepository.save(slotSubject);
//        }
        slot.setPassword(newSlot.getPassword());

        slotRepository.save(slot);
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.OK, "UPDATE SLOT SUCCESSFULLY", mapSlot.convertSlotToSlotDTO(slot));
        return responseDTO;
    }

    public ResponseDTO deleteSlot(int id) {
        Slot slot = slotRepository.findById(id).orElseThrow();
        List<Booking> booking = bookingRepository.findBySlotIdAndToggleAndStatus(id, true,1);
        if(!booking.isEmpty()){
            return new ResponseDTO(HttpStatus.OK, "DELETE SLOT FAILED", "BookingExist");
        }
        if(slot.isToggle()){
            slot.setToggle(false);
            slot.setStatus(false);
            slotRepository.save(slot);
        }
        return new ResponseDTO(HttpStatus.OK, "DELETE SLOT SUCCESSFULLY", "");
    }
    public ResponseDTO importFromExcel(List<ExcelDataDTO> excelDataDTOS, String id) {
        int success = 0;
        int failed = 0;
            for (ExcelDataDTO excelDataDTO: excelDataDTOS) {
                if(excelDataDTO == null){
                    failed++;
                    continue;
                }
                // khởi tạo slotDTO để lấy gia trị từ array object
                SlotDTO slotdto = new SlotDTO();
                //MeetingDay
                if(excelDataDTO.getMeetingDay().isBefore(LocalDate.now())
                || excelDataDTO.getMeetingDay().isAfter(LocalDate.now().plusDays(30))){
                    failed++;
                    continue;
                }else {
                    slotdto.setMeetingDay(excelDataDTO.getMeetingDay());
                }
                //StartTime
                LocalTime temp = excelDataDTO.getStartTime();
                if(temp.isAfter(excelDataDTO.getEndTime())){
                    excelDataDTO.setStartTime(excelDataDTO.getEndTime());
                    excelDataDTO.setEndTime(temp);
                }
                slotdto.setStartTime(excelDataDTO.getStartTime());
                //EndTime
                slotdto.setEndTime(excelDataDTO.getEndTime());
                if(slotdto.getStartTime().plusMinutes(135).isBefore(slotdto.getEndTime())){
                    failed++;
                    continue;
                }
                //LocationId
                if(excelDataDTO.getLocationId().equalsIgnoreCase("online")){
                    slotdto.setOnline(true);
                }else {
                    Optional<Location> location = locationRepository.findById(Integer.valueOf(excelDataDTO.getLocationId()));
                    if (location.isPresent()) {
                        Location location1 = location.get();
                        slotdto.setLocationId(location1.getId());
                        slotdto.setLocationName(location1.getName());
                        slotdto.setLocationAddress(location1.getAddress());
                    }else {
                        failed++;
                        continue;
                    }
                }
                //SubjectList
                List<Slot_SubjectDTO> subjectList = new ArrayList<>();
                String[] data = excelDataDTO.getSubjects().split(",");
                for (String pt : data) {
                    System.out.println("Subject: " + pt);
                    Subject subject = subjectRepository.findByCode(pt.trim());
                    if (subject.getCode() != null) {
                        Slot_SubjectDTO slotSubjectDTO = new Slot_SubjectDTO();
                        slotSubjectDTO.setSubjectCode(subject.getCode());
                        subjectList.add(slotSubjectDTO);
                    }
                }
                slotdto.setSlotSubjectDTOS(subjectList);
                //SlotMode
                if(excelDataDTO.getMode() > 2){
                    excelDataDTO.setMode(1);
                }
                slotdto.setMode(excelDataDTO.getMode());
                //StudentName
                if(!excelDataDTO.getStudentEmail().isEmpty() || !excelDataDTO.getStudentEmail().isBlank()) {
                    Optional<Student> student = Optional.ofNullable(studentRepository.findByEmail(excelDataDTO.getStudentEmail()));
                    if(student.isPresent()) {
                        Student student1 = student.get();
                        slotdto.setStudentEmail(student1.getEmail());
                        slotdto.setStudentName(student1.getName());
                        slotdto.setStatus(false);
                    }else {
                        failed++;
                        continue;
                    }
                }
                //SlotPassword
                if (excelDataDTO.getPassword() != "") slotdto.setPassword(excelDataDTO.getPassword());
                //lecturerId
                Optional<Lecturer> lecturer = lecturerRepository.findById(id);
                if(lecturer.isPresent()){
                    Lecturer lecturer1 = lecturer.get();
                    slotdto.setLecturerId(lecturer1.getId());
                    slotdto.setLecturerName(lecturer1.getName());
                }
                else {
                    failed++;
                    continue;
                }
                //check valid start time for new slot
                System.out.println(slotdto);
                boolean flag = utility.checkValidTime(slotdto);
                if(!flag){
                    failed++;
                    continue;
                }else success++;
                //Map DTO vào Entity, tiến hành lưu thông tin vào DB
                Slot slot1 = modelMapper.map(slotdto, Slot.class);
                Slot slot = new Slot();
                slot.setPassword(slot1.getPassword());
                slot.setLecturer(slot1.getLecturer());
                if(!slotdto.isOnline()){
                    slot.setLocation(slot1.getLocation());
                }else slot.setOnline(true);
                slot.setStartTime(slot1.getStartTime());
                slot.setEndTime(slot1.getEndTime());
                slot.setMeetingDay(slot1.getMeetingDay());
                slot.setMode(slot1.getMode());
                if(slot1.getMode() == 2) slot.setStatus(false);
                slot = slotRepository.save(slot);
                //Subject
                for (Slot_SubjectDTO slotSubjectDTO : slotdto.getSlotSubjectDTOS()) {
                    Subject subject = subjectRepository.findByCode(slotSubjectDTO.getSubjectCode());
                    Slot_Subject slotSubject = new Slot_Subject(slot, subject);
                    System.out.println("==============================");
                    System.out.println("slotSubject: " + slotSubject);
                    System.out.println("==============================");
                    slotSubjectRepository.save(slotSubject);
                }
                //Booking
                if(!excelDataDTO.getStudentEmail().isEmpty() && excelDataDTO.getMode() == 2
                        || !excelDataDTO.getStudentEmail().isBlank() && excelDataDTO.getMode() == 2) {
                    for (Slot_SubjectDTO slotSubjectDTO : slotdto.getSlotSubjectDTOS()) {
                        Subject subject = subjectRepository.findByCode(slotSubjectDTO.getSubjectCode());
                        Student student = studentRepository.findByEmail(excelDataDTO.getStudentEmail());
                        Booking booking = new Booking(slot, student, subject,2);
                        bookingRepository.save(booking);
                        emailSenderService.sendHtmlEmail(student.getEmail(), booking, 3, booking.getSlot().isOnline());
                    }


                }
            }
        return new ResponseDTO(HttpStatus.OK, "Slots added successfully!", "Success: "+ success + ", failed: " + failed);
    }
}
