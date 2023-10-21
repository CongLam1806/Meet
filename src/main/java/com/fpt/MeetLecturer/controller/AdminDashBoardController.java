package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/api/dashboard/admin")
public class AdminDashBoardController {
    @Autowired(required = false)
    private SlotRepository slotRepository;
    @Autowired(required = false)
    private LecturerRepository lecturerRepository;
    @Autowired(required = false)
    private StudentRepository studentRepository;
    @Autowired(required = false)
    private BookingRepository bookingRepository;
    @Autowired(required = false)
    private AccountRepository accountRepository;

    @GetMapping("/indicator")
    public Map<String, Long> dashboardIndicatorDisplay() {
        Map<String, Long> response = new HashMap<>();
        long createdSlot = slotRepository.countByToggle(true);
        response.put("createdSlot",createdSlot);
        long totalLecturer = lecturerRepository.countByStatus(true);
        response.put("totalLecturer",totalLecturer);
        long totalStudent = studentRepository.countByStatus(true);
        response.put("totalStudent",totalStudent);
        long totalMeeting = bookingRepository.countByToggleAndStatus(true,2);
        response.put("totalMeeting",totalMeeting);
        return response;
    }
    @GetMapping("/graph")
    public Map<String, Long> dashboardGraphDisplay() {
        Map<String, Long> response = new HashMap<>();
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        YearMonth currentMonth = YearMonth.from(today);
        for (int i = 6; i > 0; i--) {
            String key = currentMonth.getMonthValue() + "/" + currentMonth.getYear();
            long value = slotRepository.countByToggleAndMeetingDay(currentMonth.getYear(), currentMonth.getMonthValue());
            response.put(key, value);

            if (currentMonth.getMonthValue() == 1) {
                currentMonth = currentMonth.minusYears(1).plusMonths(11);
            } else {
                currentMonth = currentMonth.minusMonths(1);
            }
        }

        return response;
    }//http://localhost:8080/swagger-ui/index.html#/admin-dash-board-controller/dashboardIndicatorDisplay
}
