package com.fpt.MeetLecturer.controller;

import com.fpt.MeetLecturer.business.DashBoardChart;
import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.repository.LocationRepository;
import com.fpt.MeetLecturer.repository.SlotRepository;
import com.fpt.MeetLecturer.service.LecturerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path="/api/lecturer")
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;
    @Autowired(required = false)
    private SlotRepository slotRepository;

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
    @GetMapping("/graph/{id}")
    public DashBoardChart[] dashboardGraphDisplay(@PathVariable String id) {
        DashBoardChart[] response = new DashBoardChart[6];
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        YearMonth currentMonth = YearMonth.from(today);
        for (int i = 6; i > 0; i--) {
            String key = currentMonth.getMonthValue() + "/" + currentMonth.getYear();
            long value = slotRepository.countByToggleAndMeetingDayForLecturer(currentMonth.getYear(), currentMonth.getMonthValue(), id);
            DashBoardChart dashBoardChart = new DashBoardChart();
            dashBoardChart.setMonth(key);
            dashBoardChart.setSlotCount(value);
            response[6-i] = dashBoardChart;
            if (currentMonth.getMonthValue() == 1) {
                currentMonth = currentMonth.minusYears(1).plusMonths(11);
            } else {
                currentMonth = currentMonth.minusMonths(1);
            }
        }
        return response;
    }
}
