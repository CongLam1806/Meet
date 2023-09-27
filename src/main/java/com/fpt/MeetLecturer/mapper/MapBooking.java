package com.fpt.MeetLecturer.mapper;


import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Lecturer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapBooking {

    private static final ModelMapper modelMapper = new ModelMapper();

    public BookingDTO convertBookingtoBookingDTO(Booking booking){
        return modelMapper.map(booking, BookingDTO.class);
    }

    public List<BookingDTO> convertListToBookingDto(List<Booking> bookings){
        List<BookingDTO> list = new ArrayList<>();
        bookings.forEach(booking -> list.add(convertBookingtoBookingDTO(booking)));
        return list;
    }

    public Booking convertBookingDTOtoBooking(BookingDTO bookingDTO){
        return  modelMapper.map(bookingDTO, Booking.class);
    }

    public List<Booking> convertListToLecturer(List<BookingDTO> bookingDTOS){
        List<Booking> list = new ArrayList<>();
        bookingDTOS.forEach(bookingDTO -> list.add(convertBookingDTOtoBooking(bookingDTO)));
        return list;
    }
}
