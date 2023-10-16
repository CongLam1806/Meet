package com.fpt.MeetLecturer.controller;


import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ResponseDTO> getBookingById(@PathVariable int id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping("")
    public ResponseEntity<ResponseDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        return bookingService.createBooking(bookingDTO);
    }

    @PutMapping("")
    public ResponseEntity<ResponseDTO> updateBooking(@Valid @RequestBody BookingDTO bookingDTO) {
        return bookingService.updateBooking(bookingDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteBooking(@PathVariable int id) {
        return bookingService.deleteBooking(id);
    }
}
