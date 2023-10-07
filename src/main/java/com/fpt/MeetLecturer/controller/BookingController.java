package com.fpt.MeetLecturer.controller;


import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("")
    public List<BookingDTO> getAllBooking(){
        return bookingService.getAllBooking();
    }

    @GetMapping("/{id}")
    public BookingDTO getBookingById(@PathVariable int id){
        return bookingService.getBookingById(id);
    }

    @PostMapping("")
    public void createBooking(@Valid @RequestBody BookingDTO bookingDTO){
        bookingService.createBooking(bookingDTO);
    }

    @PutMapping("")
    public void updateBooking(@Valid @RequestBody BookingDTO bookingDTO){
        bookingService.updateBooking(bookingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable int id){
        bookingService.deleteBooking(id);
    }
}
