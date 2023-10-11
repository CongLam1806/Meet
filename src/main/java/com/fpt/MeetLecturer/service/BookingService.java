package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.mapper.MapBooking;
import com.fpt.MeetLecturer.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<ResponseDTO> getBookingById(int id){
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()){
            Booking existingBooking = booking.get();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK,
                            "Get all booking info successfully",
                            mapBooking.convertBookingtoBookingDTO(existingBooking))
            );
        } else {
            throw new RuntimeException("can't find this booking slot by id");
        }

    }

    public ResponseEntity<ResponseDTO> createBooking(BookingDTO bookingDTO){
        Optional<Booking> booking1 = bookingRepository.findById(bookingDTO.getId());
        if (booking1.isPresent()){
            throw new IllegalStateException("this slot has already booked");
        } else {
            Booking booking = new ModelMapper().map(bookingDTO, Booking.class);
            bookingRepository.save(booking);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Booking successfully","")
            );
        }
    }

    public ResponseEntity<ResponseDTO> updateBooking(BookingDTO booking){
        Optional<Booking> bookingOptional = bookingRepository.findById(booking.getId());
        if (bookingOptional.isPresent()){
            Booking existingLecturer = bookingOptional.get();
            existingLecturer.setNote(booking.getNote());
            existingLecturer.setStatus(booking.getStatus());
            bookingRepository.save(existingLecturer);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Update successfully", "")
            );
        } else {
            throw new RuntimeException("Can't find this booking slot");
        }
    }

    public ResponseEntity<ResponseDTO> deleteBooking(int id) {
        boolean exist = bookingRepository.existsById(id);
        if (!exist){
            throw new IllegalStateException("Can't delete with id " + id);
        }
        bookingRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO(HttpStatus.OK, "Delete successfully", "")
        );
    }
}
