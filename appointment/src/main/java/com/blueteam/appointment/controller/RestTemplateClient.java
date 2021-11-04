package com.blueteam.appointment.controller;

import com.blueteam.appointment.dto.NotificationDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateClient {
    private static final String NOTIFY_DOCTOR_API = "http://localhost:8082/notifier/notify/doctor";
    private static final String NOTIFY_PATIENT_API = "http://localhost:8082/notifier/notify/patient";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public static void sendNotificationToDoctor(NotificationDTO notificationDTO) {
        REST_TEMPLATE.postForEntity(NOTIFY_DOCTOR_API, notificationDTO, Void.class);
    }

    public static void sendNotificationToPatient(NotificationDTO notificationDTO) {
        REST_TEMPLATE.postForEntity(NOTIFY_PATIENT_API, notificationDTO, Void.class);
    }

}
