package com.blueteam.notifier.service;

import com.blueteam.notifier.entity.DoctorContacts;
import com.blueteam.notifier.entity.Notification;
import com.blueteam.notifier.mail.EmailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertingService {

    private final EmailService emailService;

    public AlertingService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void alert(Notification notification, String alertMsg) {

        List<DoctorContacts> contacts = notification.getDoctorContacts();
        for (DoctorContacts c : contacts) {
            String smsText = alertMsg + ". Heart rate is: " +
                    notification.getHemodynamica().getHeartRate() + ", SpO2 is: " +
                    notification.getHemodynamica().getSaturation();
            String mailText = alertMsg + ". Heart rate is: " +
                    notification.getHemodynamica().getHeartRate() +
                    ", SpO2 is: " + notification.getHemodynamica().getSaturation();
            emailService.sendEmail(c.getDoctorsEmail(), "Health condition alerting", mailText);
        }

    }
}
