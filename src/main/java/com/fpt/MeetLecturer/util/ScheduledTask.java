package com.fpt.MeetLecturer.util;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@EnableScheduling
@Component
public class ScheduledTask {
    @Autowired(required = false)
    private SlotRepository slotRepository;
    @Autowired(required = false)
    private MapSlot mapSlot;
    @Scheduled(fixedRate = 600000)//600000 = 10 minutes
    private void runTask(){
        System.out.println("autoUpdateSlotStatus");
        //get current date at Ho_Chi_Minh - Vietnam
        LocalDate current = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        //get current time at Ho_Chi_Minh - Vietnam
        LocalTime currentTime = LocalTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));

        List<Slot> workingList = slotRepository.findAll();//mapSlot.convertListToSlotDTO(slotRepository.findAll());
        for(Slot ex: workingList){
            Date temp = ex.getMeetingDay();//slot meeting date
            //converted to LocalDate type:
            LocalDate convertedDate = temp.toInstant().atZone(ZoneId.of("Asia/Ho_Chi_Minh")).toLocalDate();

            //current date is after meeting date == true
            if(convertedDate.isBefore(current)){
                ex.setStatus(false); //unavailable status
            }

            Time tmp = ex.getStartTime();
            LocalTime convertedTime = tmp.toLocalTime();

            ////current date equals meeting date AND meeting time is before current time
            if(convertedDate.equals(current) && convertedTime.isBefore(currentTime)){
                ex.setStatus(false);//unavailable status
            }
        }
        slotRepository.saveAll(workingList);
        //slotRepository.saveAll(mapSlot.toSlotList(workingList));
    }
}
