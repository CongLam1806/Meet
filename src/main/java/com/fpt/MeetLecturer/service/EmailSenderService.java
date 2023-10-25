package com.fpt.MeetLecturer.service;

import com.fpt.MeetLecturer.business.BookingDTO;
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
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    private static final String UTF_8_ENCODING = "UTF-8";
    private static final String EMAIL_ACCEPT_TEMPLATE = "sendEmailAccept.html";
    private static final String EMAIL_DECLINE_TEMPLATE = "sendEmailDecline.html";
    private static final String SUBJECT = "[MML] BOOKING CONFIRMATION MAIL";
    private static final String fromEmail = "Meeting My Lecturer <MeetLecturer@gmail.com>";



    public void sendHtmlEmail(String toEmail, BookingDTO body, int Switch){
        try {
            Context context = new Context();
            context.setVariable("studentName", body.getStudentInfo().getStudentName());
            context.setVariable("location", body.getSlotInfo().getLocationName());
            context.setVariable("startTime", body.getSlotInfo().getStartTime());
            context.setVariable("endTime", body.getSlotInfo().getEndTime());
            context.setVariable("meetingDate", body.getSlotInfo().getMeetingDate());
            context.setVariable("lecturerName", body.getSlotInfo().getLecturerName());
            context.setVariable("address", body.getSlotInfo().getLocationAddress());
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(SUBJECT);
            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            if (Switch == 1){
                String accept = templateEngine.process(EMAIL_ACCEPT_TEMPLATE, context);
                helper.setText(accept, true);
            }
            else {
                String decline = templateEngine.process(EMAIL_DECLINE_TEMPLATE, context);
                helper.setText(decline, true);
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
