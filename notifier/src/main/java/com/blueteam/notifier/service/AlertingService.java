package com.blueteam.notifier.service;

import com.blueteam.notifier.entity.DoctorContacts;
import com.blueteam.notifier.entity.Notification;

import java.util.List;

public class AlertingService {

    public static void alert(Notification notification, String alertMsg) {
        List<DoctorContacts> contacts = notification.getDoctorContacts();
        for (DoctorContacts c : contacts) {
            System.out.println("Sending sms to: " + c.getDoctorsPhoneNumber() +
                    ", with text: " + alertMsg + ". Heart rate is: " +
                    notification.getHemodynamica().getHeartRate() + ", SpO2 is: " +
                    notification.getHemodynamica().getSaturation());
            System.out.println("Sending email to: " + c.getDoctorsEmail() +
                    ", with text: " + alertMsg + ". Heart rate is: " +
                    notification.getHemodynamica().getHeartRate() +
                    ", SpO2 is: " + notification.getHemodynamica().getSaturation());
        }

    }
}
