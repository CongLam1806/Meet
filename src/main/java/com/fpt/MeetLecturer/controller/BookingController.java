package com.fpt.MeetLecturer.controller;


import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.service.BookingService;
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

    @PostMapping("/create")
    public void createBooking(@RequestBody BookingDTO bookingDTO){
        bookingService.createBooking(bookingDTO);
    }

    @PutMapping("/update")
    public void updateBooking(@RequestBody BookingDTO bookingDTO){
        bookingService.updateBooking(bookingDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBooking(@PathVariable int id){
        bookingService.deleteBooking(id);
    }
}
