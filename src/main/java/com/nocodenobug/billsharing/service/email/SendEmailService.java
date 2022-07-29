package com.nocodenobug.billsharing.service.email;
import com.nocodenobug.billsharing.payload.request.EmailDetails;

public interface SendEmailService {
    void sendSimpleMail(EmailDetails details);
}
