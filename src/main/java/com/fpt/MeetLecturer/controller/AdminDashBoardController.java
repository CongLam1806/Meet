package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.DashBoardChartDTO;
import com.fpt.MeetLecturer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

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
        long totalAdmin = accountRepository.countByRoleAndStatus(0, true);
        response.put("totalAdmin", totalAdmin);
        return response;
    }
    //http://localhost:8080/swagger-ui/index.html#/admin-dash-board-controller/dashboardIndicatorDisplay
    @GetMapping("/graph")
    public DashBoardChartDTO[] dashboardGraphDisplay2() {
        DashBoardChartDTO[] response = new DashBoardChartDTO[6];
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        YearMonth currentMonth = YearMonth.from(today);
        for (int i = 6; i > 0; i--) {
            String key = currentMonth.getMonthValue() + "/" + currentMonth.getYear();
            long value = slotRepository.countByToggleAndMeetingDay(currentMonth.getYear(), currentMonth.getMonthValue());
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
    @GetMapping("/graph/slot/week")
    public DashBoardChartDTO[] dashboardGraphDisplay(/*@PathVariable("week") int week*/) {
        int length = 15;
        DashBoardChartDTO[] response = new DashBoardChartDTO[length];
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusWeeks(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i < length; i++) {
            LocalDate weekStart = currentDate.minusWeeks(i).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate weekEnd = currentDate.minusWeeks(i).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            long value = slotRepository.countByWeekForAdmin(weekStart,weekEnd);
            String key = weekStart.format(formatter) + " - " + weekEnd.format(formatter);
            DashBoardChartDTO dashBoardChartDTO = new DashBoardChartDTO();
            dashBoardChartDTO.setMonth(key);
            dashBoardChartDTO.setSlotCount(value);
            response[i] = dashBoardChartDTO;

        }
        return response;
    }
//    @GetMapping("/graph/week")
//    public DashBoardChartDTO[] dashboardGraphDisplay3(){
//        DashBoardChartDTO[] response = new DashBoardChartDTO[15];
//        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh")).plusWeeks(5);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        WeekFields weekFields = WeekFields.of(Locale.getDefault());
//        int weekNumber = currentDate.get(weekFields.weekOfWeekBasedYear());
//        for (int i = 0; i < 15; i++) {
//            int weekNumberCheck = weekNumber - i;
//            if(weekNumberCheck == 0) {
//                weekNumberCheck = 52;
//                LocalDate weekStart = LocalDate.ofYearDay(currentDate.getYear() - 1, 1)
//                        .with(weekFields.weekOfYear(), weekNumberCheck)
//                        .with(weekFields.dayOfWeek(), 2)
//                        .atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate(); // Ngày bắt đầu của tuần (Thứ Hai)
//                LocalDate weekEnd = weekStart.plusDays(6);
//                long value = slotRepository.countByWeekForAdmin(weekStart,weekEnd);
//                String key = weekStart.format(formatter) + " - " + weekEnd.format(formatter);
//                DashBoardChartDTO dashBoardChartDTO = new DashBoardChartDTO();
//                dashBoardChartDTO.setMonth(key);
//                dashBoardChartDTO.setSlotCount(value);
//                response[i] = dashBoardChartDTO;
//            }else if(weekNumberCheck == -1){
//                weekNumberCheck = 51;
//                LocalDate weekStart = LocalDate.ofYearDay(currentDate.getYear() - 1, 1)
//                        .with(weekFields.weekOfYear(), weekNumberCheck)
//                        .with(weekFields.dayOfWeek(), 2)
//                        .atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate();// Ngày bắt đầu của tuần (Thứ Hai)
//                LocalDate weekEnd = weekStart.plusDays(6);
//                long value = slotRepository.countByWeekForAdmin(weekStart,weekEnd);
//                String key = weekStart.format(formatter) + " - " + weekEnd.format(formatter);
//                DashBoardChartDTO dashBoardChartDTO = new DashBoardChartDTO();
//                dashBoardChartDTO.setMonth(key);
//                dashBoardChartDTO.setSlotCount(value);
//                response[i] = dashBoardChartDTO;
//            }else if(weekNumberCheck == -2){
//                weekNumberCheck = 50;
//                LocalDate weekStart = LocalDate.ofYearDay(currentDate.getYear() - 1, 1)
//                        .with(weekFields.weekOfYear(), weekNumberCheck)
//                        .with(weekFields.dayOfWeek(), 2)
//                        .atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate(); // Ngày bắt đầu của tuần (Thứ Hai)
//                LocalDate weekEnd = weekStart.plusDays(6);
//                long value = slotRepository.countByWeekForAdmin(weekStart,weekEnd);
//                String key = weekStart.format(formatter) + " - " + weekEnd.format(formatter);
//                DashBoardChartDTO dashBoardChartDTO = new DashBoardChartDTO();
//                dashBoardChartDTO.setMonth(key);
//                dashBoardChartDTO.setSlotCount(value);
//                response[i] = dashBoardChartDTO;
//            } else {
//                LocalDate weekStart = LocalDate.ofYearDay(currentDate.getYear(), 1)
//                        .with(weekFields.weekOfYear(), weekNumberCheck)
//                        .with(weekFields.dayOfWeek(), 2)
//                        .atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate(); // Ngày bắt đầu của tuần (Thứ Hai)
//                LocalDate weekEnd = weekStart.plusDays(6); // Ngày kết thúc của tuần (Chủ Nhật)
//                long value = slotRepository.countByWeekForAdmin(weekStart,weekEnd);
//                String key = weekStart.format(formatter) + " - " + weekEnd.format(formatter);
//                DashBoardChartDTO dashBoardChartDTO = new DashBoardChartDTO();
//                dashBoardChartDTO.setMonth(key);
//                dashBoardChartDTO.setSlotCount(value);
//                response[i] = dashBoardChartDTO;
//            }
//        }
//        return response;
//    }
}
