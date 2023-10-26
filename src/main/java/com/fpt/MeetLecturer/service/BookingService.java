package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.mapper.GenericMap;
import com.fpt.MeetLecturer.mapper.MapBooking;
import com.fpt.MeetLecturer.repository.BookingRepository;
import com.fpt.MeetLecturer.repository.SlotRepository;
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
    private EmailSenderService emailSenderService;

    @Autowired
    private MapBooking mapBooking;
    @Autowired
    private SlotRepository slotRepository;


    public List<BookingDTO> getAllBooking() {
        return mapBooking.convertListToBookingDTO(bookingRepository.findAll());
    }

    public List<BookingDTO> getAvailableBooking(String id) {
        return mapBooking.convertListToBookingDTO(bookingRepository.findByToggleAndStatusAndSlotLecturerId(true, 1, id));
    }

    public List<BookingDTO> getUpCommingMeeting(String id) {
        List<Booking> bookingList = bookingRepository.findBySlotStatusAndToggleAndStudentId(true, true, id);
        return mapBooking.convertListToBookingDTO(bookingList);
    }

    public List<BookingDTO> getPastMeeting(String id) {
        List<Booking> bookingList = bookingRepository.findBySlotStatusAndToggleAndStudentId(true, true, id);
        return mapBooking.convertListToBookingDTO(bookingList);
    }

    public List<BookingDTO> getAllBookingByStudentId(String id) {
        return mapBooking.convertListToBookingDTO(bookingRepository.findAllByStudentIdAndToggle(id, true));
    }

    public long countByLecturerId(String id) {
        return bookingRepository.countByStatusAndSlotLecturerId(1, id);
    }

    public boolean checkStudentBooking(String studentId, int slotId) {
        return bookingRepository.existsByStudentIdAndSlotId(studentId, slotId);
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

    public ResponseEntity<ResponseDTO> setStatusWhenBooking(BookingDTO booking, int id) {
        Booking bookingEntity = genericMap.ToEntity(booking, Booking.class);
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking existingBooking = bookingOptional.get();
            if (booking.getStatus() == 2) {
                existingBooking.setStatus(bookingEntity.getStatus());
                bookingRepository.save(existingBooking);
                emailSenderService.sendHtmlEmail(existingBooking.getStudent().getEmail(), existingBooking, 1);

                Optional<Slot> slot = slotRepository.findById(booking.getSlotInfo().getId());
                if (slot.isPresent()){
                    Slot existingSlot = slot.get();
                    existingSlot.setStatus(false);
                    slotRepository.save(existingSlot);
                }

                List<Booking> bookingList = bookingRepository.findBySlotIdAndToggleAndStatus(booking.getSlotInfo().getId(), true, 1);
                for (Booking eachOfBookingList : bookingList) {
                    eachOfBookingList.setStatus(0);
                    bookingRepository.save(eachOfBookingList);
                    emailSenderService.sendHtmlEmail(eachOfBookingList.getStudent().getEmail(), eachOfBookingList, 2);
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseDTO(HttpStatus.OK, "Accept successfully", "")
                );
            }
            if (booking.getStatus() == 0){
                existingBooking.setStatus(0);
                bookingRepository.save(existingBooking);
                emailSenderService.sendHtmlEmail(existingBooking.getStudent().getEmail(), existingBooking, 2);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseDTO(HttpStatus.OK, "Decline successfully", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "No change", "")
            );
        } else {
            throw new RuntimeException("Can't find this booking information");
        }
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
