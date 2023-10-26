package com.fpt.MeetLecturer.controller;


import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("")
    public List<BookingDTO> getAllBooking() {
        return bookingService.getAllBooking();
    }


    @GetMapping("/{id}")
    public List<BookingDTO> getAllBookingBySubjectId(@PathVariable String id) {
        return bookingService.getAllBookingByStudentId(id);
    }

    @GetMapping("/pending/{id}")
    public List<BookingDTO> getPendingBooking(@PathVariable String id) {
        return bookingService.getAvailableBooking(id);
    }

//    @GetMapping("/upmeeting")
//    public List<BookingDTO> getUpCommingMeeting(@RequestParam String id) {
//        return bookingService.getUpCommingMeeting(id);
//    }
//
//    @GetMapping("/pastmeeting")
//    public List<BookingDTO> getPastMeeting(@RequestParam String id) {
//        return bookingService.getPastMeeting(id);
//    }

    @GetMapping("/count/{id}")
    public Map<String, Long> countByLecturerId(@PathVariable String id) {
        Map<String, Long> response = new HashMap<>();
        long bookingCount = bookingService.countByLecturerId(id);
        response.put("bookingCount", bookingCount);
        return response;
    }

    @GetMapping("/exists")
    public Map<String, Boolean> countByLecturerId(@RequestParam String studentId,@RequestParam  int slotId) {
        Map<String, Boolean> response = new HashMap<>();
        boolean bookingCount = bookingService.checkStudentBooking(studentId,slotId);
        response.put("exists", bookingCount);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateBooking(@Valid @RequestBody BookingDTO bookingDTO, @PathVariable int id) {
        return bookingService.updateBooking(bookingDTO, id);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ResponseDTO> setStatusBooking(@RequestBody BookingDTO bookingDTO, @PathVariable int id) {
        return bookingService.setStatusWhenBooking(bookingDTO, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteBooking(@PathVariable int id) {
        return bookingService.deleteBooking(id);
    }


}
