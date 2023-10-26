package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.DashBoardChartDTO;
import com.fpt.MeetLecturer.business.DashBoardIndicatorLecturerDTO;
import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.repository.LocationRepository;
import com.fpt.MeetLecturer.repository.SlotRepository;
import com.fpt.MeetLecturer.service.LecturerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(path="/api/lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;
    @Autowired(required = false)
    private SlotRepository slotRepository;
    @Autowired(required = false)
    private LocationRepository locationRepository;

    @GetMapping("")
    public List<LecturerDTO> getAllLecturer(){
        return lecturerService.getAllLecturer();
    }

    @GetMapping("/status")
    public List<LecturerDTO> getAllLecturerByStatus(){
        return lecturerService.getAllLecturerByStatus();
    }

    @GetMapping("/{id}")
    public LecturerDTO getAllLecturerByEmail(@PathVariable String id){
        return lecturerService.getLecturerById(id);
    }

//    @PostMapping("")
//    public ResponseEntity<ResponseDTO> createLecturer(@RequestBody @Valid LecturerDTO lecturerDTO){
//       return lecturerService.createLecturer(lecturerDTO);
//    }
//

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateLecturer(@RequestBody @Valid LecturerDTO newLecturer, @PathVariable String id ){
         return lecturerService.updateLecturer(newLecturer, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteLecturer(@PathVariable String id ){
        return lecturerService.deleteLecturer(id);
    }
    @GetMapping("/indicator/{id}")
    public ResponseEntity<DashBoardIndicatorLecturerDTO> dashboardIndicatorDisplay(@PathVariable String id) {
        DashBoardIndicatorLecturerDTO indicator = new DashBoardIndicatorLecturerDTO();
        long totalSlot = slotRepository.countByLecturerIdAndToggle(id, true);
        indicator.setTotalSlot(totalSlot);
        Time totalHours = slotRepository.totalMeetingTime(id);
        indicator.setTotalHours(totalHours);
        long totalLocation = locationRepository.countByLecturerIdAndToggle(id, true);
        indicator.setTotalLocation(totalLocation);
        String mostDiscussSubject = slotRepository.mostDiscussSubjectLecturer(id);
        indicator.setMostDiscussSubject(mostDiscussSubject);
        return  ResponseEntity.ok().body(indicator);
    }
    @GetMapping("/graph/{id}")
    public ResponseEntity<DashBoardChartDTO[]> dashboardGraphDisplay(@PathVariable String id) {
        DashBoardChartDTO[] response = new DashBoardChartDTO[6];
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        YearMonth currentMonth = YearMonth.from(today);
        YearMonth currentWeek = YearMonth.from(today);
        for (int i = 6; i > 0; i--) {
            String key = currentMonth.getMonthValue() + "/" + currentMonth.getYear();
            long value = slotRepository.countByToggleAndMeetingDayForLecturer(currentMonth.getYear(), currentMonth.getMonthValue(), id);
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
        return  ResponseEntity.ok().body(response);
    }
    @GetMapping("/graph/week/{id}")
    public ResponseEntity<DashBoardChartDTO[]>dashboardGraphDisplay2(@PathVariable String id) {
        DashBoardChartDTO[] response = new DashBoardChartDTO[4];
        LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i < 4; i++) {
            LocalDate weekStart = currentDate.minusWeeks(i).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate weekEnd = currentDate.minusWeeks(i).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            long value = slotRepository.countByWeekForLecturer(weekStart,weekEnd);
            String key = weekStart.format(formatter) + " - " + weekEnd.format(formatter);
            DashBoardChartDTO dashBoardChartDTO = new DashBoardChartDTO();
            dashBoardChartDTO.setMonth(key);
            dashBoardChartDTO.setSlotCount(value);
            response[i] = dashBoardChartDTO;
        }

        return ResponseEntity.ok().body(response);
    }
}
