package com.musicsite.repository;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}
