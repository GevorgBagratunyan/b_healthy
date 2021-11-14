package com.blueteam.appointment.service;

import com.blueteam.appointment.controller.RestTemplateClient;
import com.blueteam.appointment.dto.NotificationDTO;
import com.blueteam.appointment.entity.Appointment;
import com.blueteam.appointment.entity.Doctor;
import com.blueteam.appointment.entity.Patient;
import org.springframework.stereotype.Service;

@Service
public class NotifierService {

    private final RestTemplateClient restTemplateClient;

    public NotifierService(RestTemplateClient restTemplateClient) {
        this.restTemplateClient = restTemplateClient;
    }

    public void sendNotificationToPatient(Appointment appointment, Boolean isAccepted) {
        Patient patient = appointment.getPatient();
        String phoneNumber = patient.getPhoneNumber();
        String email = patient.getEmail();
        String doctorName = appointment.getDoctor().getName();
        String patientName = patient.getName();
        String subject = "Appointment";
        String msg;
        if (isAccepted) {
            msg = "Dear " + patientName +
                    ", the appointment with a doctor " + doctorName + " is accepted";
        } else {
            msg = "Dear " + patientName +
                    ", the appointment with a doctor " + doctorName + " is declined";
        }
        NotificationDTO notificationDTO = new NotificationDTO(phoneNumber, email, subject, msg);
        restTemplateClient.sendNotificationToPatient(notificationDTO);
    }

    public void sendNotificationToDoctor(Appointment appointment) {
        Doctor doctor = appointment.getDoctor();
        String phoneNumber = doctor.getPhoneNumber();
        String email = doctor.getEmail();
        String patientName = appointment.getPatient().getName();
        String doctorName = doctor.getName();
        String subject = "Appointment";
        String msg;
        msg = "Dear " + doctorName +
                ", the appointment with a patient " + patientName + " is canceled";
        NotificationDTO notificationDTO = new NotificationDTO(phoneNumber, email, subject, msg);
        restTemplateClient.sendNotificationToDoctor(notificationDTO);
    }
}
