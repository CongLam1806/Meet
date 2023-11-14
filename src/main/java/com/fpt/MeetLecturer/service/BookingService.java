package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.ResponseDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.entity.Slot_Subject;
import com.fpt.MeetLecturer.entity.Subject;
import com.fpt.MeetLecturer.mapper.GenericMap;
import com.fpt.MeetLecturer.mapper.MapBooking;
import com.fpt.MeetLecturer.repository.BookingRepository;
import com.fpt.MeetLecturer.repository.SlotRepository;
import com.fpt.MeetLecturer.repository.SlotSubjectRepository;
import com.fpt.MeetLecturer.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SlotSubjectRepository subjectRepository;

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


    public List<BookingDTO> getUpcomingMeeting(String id) {
        List<Booking> bookingList = bookingRepository
                .findUpComingSlot(LocalDate.now(), Time.valueOf(LocalTime.now()), true, id);
        return mapBooking.convertListToBookingDTO(bookingList);
    }

    public List<BookingDTO> getPastMeeting(String id) {
        List<Booking> bookingList = bookingRepository
                .findPastSlot(LocalDate.now(), Time.valueOf(LocalTime.now()),true, id);
        return mapBooking.convertListToBookingDTO(bookingList);
    }


    public List<BookingDTO> getAllBookingByStudentId(String id) {
        return mapBooking.convertListToBookingDTO(bookingRepository.findAllByStudentIdAndToggle(id, true));
    }

    public long countByLecturerId(String id) {
        return bookingRepository.countByStatusAndSlotLecturerId(1, id);
    }

    public boolean checkStudentBooking(String studentId, int slotId) {
        return bookingRepository.existsByStudentIdAndSlotIdAndToggle(studentId, slotId, true);
    }

    public ResponseEntity<ResponseDTO> createBooking(BookingDTO bookingDTO) {
        Booking bookingEntity = genericMap.ToEntity(bookingDTO, Booking.class);
        Booking booking = new Booking();
        booking.setNote(bookingEntity.getNote());
        booking.setSlot(bookingEntity.getSlot());
        booking.setStudent(bookingEntity.getStudent());

        List<Slot_Subject> subjectList = subjectRepository.findBySlotId(bookingDTO.getSlotInfo().getId());
        boolean foundSubject = false;
        for (Slot_Subject subject : subjectList) {
            if (bookingDTO.getSubject().equalsIgnoreCase(subject.getSubject().getCode())) {
                booking.setSubject(subject.getSubject());
                foundSubject = true;
                break;
            }
        }

        if (!foundSubject) {
            throw new EntityNotFoundException("Can't find this subject");
        }

        boolean canBook = canBookSlotToday(bookingDTO.getStudentInfo().getStudentId(),
                bookingDTO.getSlotInfo().getStartTime(),
                bookingDTO.getSlotInfo().getEndTime(),
                bookingDTO.getSlotInfo().getMeetingDate());
        if (canBook) {

            bookingRepository.save(booking);
            Optional<Slot> slot = slotRepository.findById(bookingEntity.getSlot().getId());
            if (slot.isPresent()) {
                Slot existingSlot = slot.get();

                if (existingSlot.getMode() == 1) {
                    booking.setStatus(2);
                    bookingRepository.save(booking);
                    existingSlot.setStatus(false);
                    slotRepository.save(existingSlot);
                }
            };

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Booking successfully", "")
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDTO(HttpStatus.OK, "Can't Book this slot because you already booked a slot that have the same time range", "sameTimeErr")
            );
        }

    }

    public ResponseEntity<ResponseDTO> setStatusWhenBooking(BookingDTO booking, int id) {
        Booking bookingEntity = genericMap.ToEntity(booking, Booking.class);
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking existingBooking = bookingOptional.get();
            if (booking.getStatus() == 2) {
                existingBooking.setStatus(bookingEntity.getStatus());
                bookingRepository.save(existingBooking);
                emailSenderService.sendHtmlEmail(existingBooking.getStudent().getEmail(), existingBooking, 1, existingBooking.getSlot().isOnline());

                Optional<Slot> slot = slotRepository.findById(booking.getSlotInfo().getId());
                if (slot.isPresent()) {
                    Slot existingSlot = slot.get();
                    existingSlot.setStatus(false);
                    slotRepository.save(existingSlot);
                }

                List<Booking> bookingList = bookingRepository.findBySlotIdAndToggleAndStatus(booking.getSlotInfo().getId(), true, 1);
                for (Booking eachOfBookingList : bookingList) {
                    eachOfBookingList.setStatus(0);
                    bookingRepository.save(eachOfBookingList);
                    emailSenderService.sendHtmlEmail(eachOfBookingList.getStudent().getEmail(), eachOfBookingList, 2, eachOfBookingList.getSlot().isOnline());
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseDTO(HttpStatus.OK, "Accept successfully", "")
                );
            }
            if (booking.getStatus() == 0) {
                existingBooking.setStatus(0);
                bookingRepository.save(existingBooking);
                emailSenderService.sendHtmlEmail(existingBooking.getStudent().getEmail(), existingBooking, 2, existingBooking.getSlot().isOnline());
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

    public boolean canBookSlotToday(String studentId, LocalTime startTime, LocalTime endTime, LocalDate date) {

        // Lấy danh sách các booking đã đặt trong ngày
        List<Booking> bookingsToday = bookingRepository.findByStudentIdAndSlotMeetingDayAndToggleAndStatusNot(studentId, date, true, 0);

        // Kiểm tra xem có bất kỳ booking nào trong khoảng thời gian đã định
        for (Booking booking : bookingsToday) {
            LocalTime bookingStartTime = booking.getSlot().getStartTime();
            LocalTime bookingEndTime = booking.getSlot().getEndTime();

            // Nếu bất kỳ booking nào nằm trong khoảng thời gian hoặc chứa khoảng thời gian đã định, không cho phép đặt lịch
            if ((startTime.isBefore(bookingEndTime) && endTime.isAfter(bookingStartTime)) ||
                    (bookingStartTime.isBefore(endTime) && bookingEndTime.isAfter(startTime))||
                    (bookingStartTime.equals(startTime) && bookingEndTime.equals(endTime)))
            {
                return false;
            }
        }

        return true;
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
