package com.fpt.MeetLecturer.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class Utility {
    final static String pattern = "[A-Z,a-z]{2}[\\d]{6}"; // Matches 2 letters followed by 6 digits
    public boolean isStudent(String Email){
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(Email);
        return matcher.find();

    }
    public static String extractStudentId(String Email){
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(Email);
        if(matcher.find()){
            return matcher.group().toUpperCase();
        }
        return null;
    }
//    public static void main(String[] args){
//        String demo = "mimndse173605@fpt.edu.vn";
//        boolean check = isStudent(demo);
//        System.out.println("check regex: " + check);
//        String Id = extractStudentId(demo);
//        System.out.println("Id extract: " + Id);
//    }
}

