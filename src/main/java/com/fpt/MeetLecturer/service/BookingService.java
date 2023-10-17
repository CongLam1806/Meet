package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.mapper.GenericMap;
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
    private GenericMap genericMap;

    @Autowired
    private MapBooking mapBooking;


    public List<BookingDTO> getAllBooking(){
        return mapBooking.convertListToBookingDTO(bookingRepository.findAll());
    }

    public List<BookingDTO> getAllBookingByStudentId(String id){
        return mapBooking.convertListToBookingDTO(bookingRepository.findAllByStudentId(id));
    }

    public ResponseEntity<ResponseDTO> getBookingBySubjectId(String id){
        Optional<Booking> booking = bookingRepository.findByStudentId(id);
        if (booking.isPresent()){
            Booking existingBooking = booking.get();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK,
                            "Get all booking info successfully",
                            mapBooking.convertBookingToBookingDTO(existingBooking))
            );
        } else {
            throw new RuntimeException("Can't find this booking slot with student's id: " + id);
        }

    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! cận thẩn slot và booking

    public ResponseEntity<ResponseDTO> createBooking(BookingDTO bookingDTO){
        Booking bookingEntity = genericMap.ToEntity(bookingDTO, Booking.class);
        Optional<Booking> booking1 = bookingRepository.findById(bookingEntity.getId());
        if (booking1.isPresent()){
            throw new IllegalStateException("this slot has already booked");
        } else {
            Booking booking = new ModelMapper().map(bookingEntity, Booking.class);
//            Booking booking = new Booking();
//            booking.setSlot(bookingDTO.);
            bookingRepository.save(booking);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Booking successfully","")
            );
        }
    }

    public ResponseEntity<ResponseDTO> updateBooking(BookingDTO booking, int id){
        Booking bookingEntity = genericMap.ToEntity(booking, Booking.class);
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()){
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
