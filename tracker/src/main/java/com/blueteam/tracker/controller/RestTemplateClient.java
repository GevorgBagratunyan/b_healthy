package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.NotificationDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateClient {
    private static final String NOTIFY_DOCTORS_API = "http://localhost:8082/notify";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();


    public static void sendNotification(NotificationDTO notificationDTO) {
        REST_TEMPLATE.postForEntity(NOTIFY_DOCTORS_API, notificationDTO, NotificationDTO.class);
    }

}
