package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.mapper.MapBooking;
import com.fpt.MeetLecturer.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MapBooking mapBooking;

    public BookingDTO getBookingById(int id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()){
            Booking existingBooking = booking.get();
            return mapBooking.convertBookingtoBookingDTO(existingBooking);
        }
    }

    public void createBooking(Booking booking){
        Optional<Booking> booking1 = bookingRepository.findById(booking.getId());
        if (booking1.isPresent()){
            throw new IllegalStateException("this slot has already booked");
        } else {
            bookingRepository.save(booking);
        }
    }

}
