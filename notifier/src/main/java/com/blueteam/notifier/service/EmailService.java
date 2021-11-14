package com.blueteam.notifier.service;

import com.blueteam.notifier.exception.notification.NotificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.rmi.NotBoundException;

@Component
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bhealthyapplication@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try{
            emailSender.send(message);
        } catch (MailParseException ex) {
            throw new NotificationException("An Exception was thrown while parsing given Email", to);
        } catch (MailAuthenticationException ex) {
            throw new NotificationException("Bad credentials of GMail account for Email sender", message.getFrom());
        } catch (MailException ex) {
            throw new NotificationException("An external Error occurred while sending Email", "No additional info");
        }
    }
}
