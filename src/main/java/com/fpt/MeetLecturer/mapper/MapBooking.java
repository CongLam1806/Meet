package com.fpt.MeetLecturer.mapper;


import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Lecturer;
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
                .addMapping(src -> src.getSlot().getStartTime(), BookingDTO::setStartTime)
                .addMapping(src -> src.getSlot().getEndTime(), BookingDTO::setEndTime)
                .addMapping(src -> src.getSlot().getMeetingDate(), BookingDTO::setMeetingDate)
                .addMapping(src -> src.getStudent().getId(), BookingDTO::setStudentId)
                .addMapping(src -> src.getSlot().getLecturer().getName(), BookingDTO::setLecturerName);
    }

}
