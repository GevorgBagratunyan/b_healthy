package com.blueteam.notifier.service;

import com.blueteam.notifier.entity.DoctorContacts;
import com.blueteam.notifier.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertingService {

    private final EmailService emailService;
    private final SmsService smsService;

    public AlertingService(EmailService emailService, SmsService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void alert(Notification notification, String alertMsg) {
        List<DoctorContacts> contacts = notification.getDoctorContacts();
        for (DoctorContacts c : contacts) {
            String emailText = "Patient with id -> " + notification.getObjId() + " " +
                    alertMsg + ".\nHeart rate is: " +
                    notification.getHemodynamica().getHeartRate() +
                    ", SpO2 is: " + notification.getHemodynamica().getSaturation();
            String smsText = "Patient with id -> " + notification.getObjId() + " " +
                    alertMsg + ".\n.Heart rate is: " +
                    notification.getHemodynamica().getHeartRate() + ", SpO2 is: " +
                    notification.getHemodynamica().getSaturation();
            String email = c.getDoctorsEmail();
            String phoneNumber = c.getDoctorsPhoneNumber();
            if (email != null) {
                emailService.sendEmail(email, "Health condition alerting", emailText);
            }
            if (phoneNumber != null) {
                smsService.sendSms(phoneNumber, smsText);
            }
        }
    }
}
