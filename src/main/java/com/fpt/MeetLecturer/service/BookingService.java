package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Lecturer;
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


    public List<BookingDTO> getAllBooking(){
        return mapBooking.convertListToBookingDto(bookingRepository.findAll());
    }

    public BookingDTO getBookingById(int id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()){
            Booking existingBooking = booking.get();
            return mapBooking.convertBookingtoBookingDTO(existingBooking);
        } else {
            throw new RuntimeException("can't find this booking slot by id");
        }

    }

    public void createBooking(BookingDTO booking){
        Optional<Booking> booking1 = bookingRepository.findById(booking.getId());
        if (booking1.isPresent()){
            throw new IllegalStateException("this slot has already booked");
        } else {
            bookingRepository.save(mapBooking.convertBookingDTOtoBooking(booking));
        }
    }

    public void updateBooking(BookingDTO booking){
        Optional<Booking> bookingOptional = bookingRepository.findById(booking.getId());
        if (bookingOptional.isPresent()){
            Booking existingLecturer = bookingOptional.get();
            existingLecturer.setNote(booking.getNote());
            existingLecturer.setStatus(booking.getStatus());
            bookingRepository.save(existingLecturer);
        } else {
            throw new RuntimeException("Can't find this booking slot");
        }
    }

    public void deleteBooking(int id) {
        boolean exist = bookingRepository.existsById(id);
        if (!exist){
            throw new IllegalStateException("student with id " + id + " does not exists");
        }
        bookingRepository.deleteById(id);
    }
}
