package com.kshrd.devconnect_springboot.service;

public interface EmailSenderService {
    void sendEmail(String toEmail, String otp);
}
