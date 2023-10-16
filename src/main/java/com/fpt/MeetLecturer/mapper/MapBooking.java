package com.fpt.MeetLecturer.mapper;


import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.entity.Slot;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapBooking {

    private static final ModelMapper modelMapper = new ModelMapper();


    static {
        TypeMap<Booking, BookingDTO> propertyMapper = modelMapper.createTypeMap(Booking.class, BookingDTO.class);
        propertyMapper
                .addMapping(src -> src.getStudent().getName(), BookingDTO::setStudentName)
                .addMapping(src -> src.getSlot().getStartTime(), BookingDTO::setStartTime)
                .addMapping(src -> src.getSlot().getEndTime(), BookingDTO::setEndTime)
                .addMapping(src -> src.getSlot().getMeetingDay(), BookingDTO::setMeetingDate)
                .addMapping(src -> src.getSlot().getLecturer().getName(), BookingDTO::setLecturerName);
    }

    public BookingDTO convertBookingToBookingDTO(Booking slot) {
        return modelMapper.map(slot, BookingDTO.class);
    }

    public List<BookingDTO> convertListToBookingDTO(List<Booking> slots) {
        List<BookingDTO> list = new ArrayList<>();
        slots.forEach(booking -> list.add(convertBookingToBookingDTO(booking)));
        return list;
    }

}
