package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.BookingDTO;
import com.fpt.MeetLecturer.business.LecturerDTO;
import com.fpt.MeetLecturer.business.LocationDTO;
import com.fpt.MeetLecturer.entity.Booking;
import com.fpt.MeetLecturer.entity.Lecturer;
import com.fpt.MeetLecturer.entity.Location;
import com.fpt.MeetLecturer.repository.LecturerRepository;
import com.fpt.MeetLecturer.repository.LocationRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.ZoneId;

@Service
public class EmailSenderService {
    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    private static final String UTF_8_ENCODING = "UTF-8";
    private static final String EMAIL_ACCEPT_TEMPLATE = "sendEmailAccept.html";
    private static final String EMAIL_DECLINE_TEMPLATE = "sendEmailDecline.html";
    private static final String EMAIL_ASSIGN_TEMPLATE = "sendEmailAssignStudent.html";
    private static final String SUBJECT = "[MML] BOOKING CONFIRMATION MAIL";
    private static final String fromEmail = "Meeting My Lecturer";



    public void sendHtmlEmail(String toEmail, Booking body, int Switch, boolean isOnline){
        LecturerDTO lecturer = lecturerService.getLecturerById(body.getSlot().getLecturer().getId());
        Location location = locationRepository.findById(body.getSlot().getLocation().getId()).orElseThrow();
        try {
            Context context = new Context();
            context.setVariable("isOnline", isOnline);
            context.setVariable("studentName", body.getStudent().getName());
            context.setVariable("startTime", body.getSlot().getStartTime());
            context.setVariable("endTime", body.getSlot().getEndTime());
            context.setVariable("meetingDate", body.getSlot().getMeetingDay());
            context.setVariable("lecturerName", lecturer.getName());

            if (!isOnline){
                context.setVariable("address", location.getAddress());
                context.setVariable("location", location.getName());
            }
            context.setVariable("linkMeet", lecturer.getLinkMeet());

            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(SUBJECT);
            helper.setFrom("truongthanhvu2337@gmail.com", fromEmail);
            helper.setTo(toEmail);
            if (Switch == 1){
                String accept = templateEngine.process(EMAIL_ACCEPT_TEMPLATE, context);
                helper.setText(accept, true);
            }
            else if (Switch == 2){
                String decline = templateEngine.process(EMAIL_DECLINE_TEMPLATE, context);
                helper.setText(decline, true);
            }
            else {
                String assign = templateEngine.process(EMAIL_ASSIGN_TEMPLATE, context);
                helper.setText(assign, true);
            }
            mailSender.send(message);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }


    private MimeMessage getMimeMessage() {
        return  mailSender.createMimeMessage();
    }
}
