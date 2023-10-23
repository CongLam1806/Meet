package com.fpt.MeetLecturer.util;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class Utility {
    @Autowired(required = false)
    private MapSlot mapSlot;
    @Autowired(required = false)
    private SlotRepository slotRepository;
    final static String pattern = "[A-Z,a-z]{2}[\\d]{6}"; // 2 characters followed by 6 digits
    final static String pattern2 = "[A-Z][\\d]{2}"; // 1 character followed by 2 digits
    final static String pattern3 = "[A-Z]{2,3}"; // 2 or 3 uppercase characters

    final static String patternIT = "[SHDC]E\\d{6}";
    final static String patternBA = "[SHDC]S\\d{6}";
    final static String patternES = "[SHDC]A\\d{6}";
    public boolean isStudent(String Email){
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(Email);
        return matcher.find();

    }
    public String extractStudentId(String Email){
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(Email);
        if(matcher.find()){
            return matcher.group().toUpperCase();
        }
        return null;
    }

    public int addMajorToStudent(String code){
        int majorId = 1;
        if(code.equals(patternIT)){
            majorId = 1;
        } else if (code.equals(patternBA)){
            majorId = 2;
        } else if(code.equals(patternES)){
            majorId = 3;
        }
        return majorId;
    }
    public  String extractCuriculum(String studentName){
        Pattern regex = Pattern.compile(pattern2);
        Matcher matcher = regex.matcher(studentName);
        if(matcher.find()){
            return matcher.group().toUpperCase();
        }
        return null;
    }
    public  String extractDefaultAddress(String studentName){
        Pattern regex = Pattern.compile(pattern3);
        Matcher matcher = regex.matcher(studentName);
        if(matcher.find()){
            return matcher.group().toUpperCase();
        }
        return null;
    }

    public boolean checkValidTime(SlotDTO newSlot){
        List<Slot> workingList = slotRepository.findByLecturerIdAndStatusOrderByMeetingDayDesc(newSlot.getLecturerId(), true);
        for(Slot ex: workingList){
            Date temp = ex.getMeetingDay();//get existing slot date
            Date newSlotDate = newSlot.getMeetingDay();//new slot date
            Time tmp = ex.getEndTime();//get existing slot end time
            Time newSlotStartTime = newSlot.getStartTime();//get new slot start time
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(tmp);//convert to calendar
            calendar.add(Calendar.MINUTE, 15);//existing slot end time + 15 minutes
            Time newTmp = new Time(calendar.getTimeInMillis());//convert back to time.
            if (newSlotDate.equals(temp) ){//date comparison
                if(newSlotStartTime.before(newTmp) || newSlotStartTime.equals(newTmp)){//time comparison
                    return false;
                }
            }
        }
        return true;
    }
//    public static void main(String[] args){
//        String[] demo = {"mimndse173605@fpt.edu.vn", "lamtcse173602@fpt.edu.vn", "chiltq01@fe.edu.vn",
//                "thanhvtse173601@fpt.edu.vn", "trungnldse173600@fpt.edu.vn", "khangthgse173607@fpt.edu.vn",
//        "Mai Nguyen Duc Minh (K17 HCM)", "Vu Truong Thanh (K17 HCM)", "Tran Cong Lam (K17 HCM)"};
//        for(String input : demo){
//            System.out.println(input);
////            boolean check = isStudent(input);
////            System.out.println("check regex: " + check);
////            String Id = extractStudentId(input);
////            System.out.println("Id extract: " + Id);
////            System.out.println("==================================");
//            String khoa = extractCuriculum(input);
//            System.out.println("Id extract: " + khoa);
//            String defaultAddress = extractDefaultAddress(input);
//            System.out.println("Default Address extract: " + defaultAddress);
//            System.out.println("==================================");
//        }
//    }
}

