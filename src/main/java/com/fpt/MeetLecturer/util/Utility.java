package com.fpt.MeetLecturer.util;

import com.fpt.MeetLecturer.business.SlotDTO;
import com.fpt.MeetLecturer.entity.Slot;
import com.fpt.MeetLecturer.mapper.MapSlot;
import com.fpt.MeetLecturer.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
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
        int majorId = 0;
        if(code.matches(patternIT)){
            majorId = 1;
        } else if (code.matches(patternBA)){
            majorId = 2;
        } else if(code.matches(patternES)){
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

//    public String lecturerFirstOfEmail(String lecturerEmail){
//
//    }
    public  String extractDefaultAddress(String studentName){
        Pattern regex = Pattern.compile(pattern3);
        Matcher matcher = regex.matcher(studentName);
        if(matcher.find()){
            return matcher.group().toUpperCase();
        }
        return null;
    }

    public boolean checkValidTime(SlotDTO newSlot){
        List<Slot> workingList = slotRepository.findByLecturerIdOrderByMeetingDayDesc(newSlot.getLecturerId());
        for (Slot ex : workingList) {
            LocalDate temp = ex.getMeetingDay(); // get existing slot date
            LocalDate newSlotDate = newSlot.getMeetingDay(); // new slot date
            LocalTime tmp = ex.getEndTime(); // get existing slot end time
            LocalTime tmp2 = ex.getStartTime();// get existing slot start time
            LocalTime newSlotStartTime = newSlot.getStartTime(); // get new slot start time
            LocalTime newSlotEndTime = newSlot.getEndTime();// get new slot end time
            LocalTime add = tmp.plusMinutes(14);
            LocalTime add2 = tmp2.minusMinutes(14);
            if (newSlotDate.equals(temp)) { // date comparison
                if(newSlotStartTime.isBefore(LocalTime.now().plusHours(6))){
                    System.out.println("Slot start time must after current time + 6 hours!!!");
                    return false;
                }
                if (newSlotEndTime.isBefore(add2)) { // time comparison
                    return true;
                }
                if (newSlotStartTime.isBefore(add) || newSlotStartTime.equals(add)) { // time comparison
                    System.out.println(ex.getMeetingDay() +" - " + newSlotDate);
                    System.out.println("not valid time (newSlotStartTime - ExistedSlotStartTime):" + newSlotStartTime + " - " + add);
                    return false;
                }
            }
            if (newSlotDate.isBefore(LocalDate.now())) {
                System.out.println(LocalDate.now());
                System.out.println("not valid date! New slot date is before current date!!");
                return false;
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

