package com.musicsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
public class EmailService {


    private JavaMailSender emailSender;
    private SimpleMailMessage template;

    @Autowired
    public EmailService(JavaMailSender emailSender,
                        SimpleMailMessage template) {
        this.emailSender = emailSender;
        this.template = template;
    }

    public JavaMailSender getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendHTMLEmail(String to, String subject, Long id, String hashUsername) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String userId = String.format(Objects.requireNonNull(template.getText()), id, hashUsername);


        helper.setText(userId, true); // Use this or above line.
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("musicsite.mailservice.com");
        emailSender.send(mimeMessage);

    }
}
