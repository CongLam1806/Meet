package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.*;
import com.fpt.MeetLecturer.repository.BookingRepository;
import com.fpt.MeetLecturer.repository.SlotRepository;
import com.fpt.MeetLecturer.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired(required = false)
    BookingRepository bookingRepository;
    @Autowired(required = false)
    SlotRepository slotRepository;

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllStudent(){
        ResponseDTO responseDTO = studentService.getAllStudent();
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/emails")
    public ResponseEntity<ResponseDTO> getStudentEmails(){
        ResponseDTO responseDTO = studentService.getActiveStudentEmail();
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getStudentById(@RequestParam(name="id") String id){
        ResponseDTO responseDTO = studentService.getStudentById(id);
        return ResponseEntity.ok().body(responseDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateStudent(@RequestBody @Valid StudentDTO model, @PathVariable("id") String id){
        ResponseDTO responseDTO = studentService.updateStudent(model, id);
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/indicator/{id}")
    public ResponseEntity<DashBoardIndicatorDTO> dashboardIndicatorDisplay(@PathVariable("id") String id){
        DashBoardIndicatorDTO dashBoardIndicatorDTO = new DashBoardIndicatorDTO();
        long totalMeeting = bookingRepository.countByStatusAndStudentId(2, id);
        dashBoardIndicatorDTO.setTotalMeeting(totalMeeting);
        Time totalHours = slotRepository.totalMeetingTimeStudent(id);
        dashBoardIndicatorDTO.setTotalHours(totalHours);
        long totalBooking = bookingRepository.countByStudentIdAndToggle(id, true);
        dashBoardIndicatorDTO.setTotalBooking(totalBooking);
        String mostDiscussSubject = bookingRepository.mostDiscussSubject(id);
        dashBoardIndicatorDTO.setMostDiscussSubject(mostDiscussSubject);
        return ResponseEntity.ok().body(dashBoardIndicatorDTO);
    }
    @GetMapping("/graph/{id}")
    public DashBoardChartDTO[] dashboardGraphDisplay2(@PathVariable("id") String id){
        DashBoardChartDTO[] response = new DashBoardChartDTO[6];
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        YearMonth currentMonth = YearMonth.from(today);
        for (int i = 6; i > 0; i--) {
            String key = currentMonth.getMonthValue() + "/" + currentMonth.getYear();
            long value = bookingRepository.countMeetingByDate(currentMonth.getYear(), currentMonth.getMonthValue(), id);
            DashBoardChartDTO dashBoardChartDTO = new DashBoardChartDTO();
            dashBoardChartDTO.setMonth(key);
            dashBoardChartDTO.setSlotCount(value);
            response[6-i] = dashBoardChartDTO;
            if (currentMonth.getMonthValue() == 1) {
                currentMonth = currentMonth.minusYears(1).plusMonths(11);
            } else {
                currentMonth = currentMonth.minusMonths(1);
            }
        }
        return response;
    }
}
