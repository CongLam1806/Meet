package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.mapper.GenericMap;
import com.fpt.MeetLecturer.mapper.MapBooking;
import com.fpt.MeetLecturer.repository.BookingRepository;
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
    private GenericMap genericMap;

    @Autowired
    private MapBooking mapBooking;


    public List<BookingDTO> getAllBooking() {
        return mapBooking.convertListToBookingDTO(bookingRepository.findAll());
    }

    public List<BookingDTO> getAllBookingByStudentId(String id) {
        return mapBooking.convertListToBookingDTO(bookingRepository.findAllByStudentIdAndToggle(id, true));
    }

    public long countByLecturerId(String id) {
        return bookingRepository.countByStatusAndSlotLecturerId(1, id);
    }


    public ResponseEntity<ResponseDTO> createBooking(BookingDTO bookingDTO) {
        Booking bookingEntity = genericMap.ToEntity(bookingDTO, Booking.class);
        Booking booking = new Booking();
        booking.setNote(bookingEntity.getNote());
        booking.setSlot(bookingEntity.getSlot());
        booking.setStudent(bookingEntity.getStudent());
        bookingRepository.save(booking);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDTO(HttpStatus.OK, "Booking successfully", "")
        );
    }


    public ResponseEntity<ResponseDTO> updateBooking(BookingDTO booking, int id) {
        Booking bookingEntity = genericMap.ToEntity(booking, Booking.class);
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking existingLecturer = bookingOptional.get();
            existingLecturer.setNote(bookingEntity.getNote());
            bookingRepository.save(existingLecturer);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Update successfully", "")
            );
        } else {
            throw new RuntimeException("Can't find this booking information");
        }
    }

    public ResponseEntity<ResponseDTO> deleteBooking(int id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            booking.setToggle(false);
            bookingRepository.save(booking);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Delete successfully", "")
            );
        } else {
            throw new IllegalStateException("student with id " + id + " does not exists");
        }
    }
}
