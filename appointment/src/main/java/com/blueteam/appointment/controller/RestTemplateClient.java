package com.blueteam.appointment.controller;

import com.blueteam.appointment.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateClient {
    @Value("${NOTIFY_DOCTOR_API}")
    private String NOTIFY_DOCTOR_API;
    @Value("${NOTIFY_PATIENT_API}")
    private String NOTIFY_PATIENT_API;
    private final RestTemplate restTemplate;

    public RestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendNotificationToDoctor(NotificationDTO notificationDTO) {
        restTemplate.postForEntity(NOTIFY_DOCTOR_API, notificationDTO, Void.class);
    }

    public void sendNotificationToPatient(NotificationDTO notificationDTO) {
        restTemplate.postForEntity(NOTIFY_PATIENT_API, notificationDTO, Void.class);
    }

}
