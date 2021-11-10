package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateClient {
    private static final String NOTIFY_DOCTORS_API = "http://localhost:8082/notifier/notify";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final Logger LOG = LoggerFactory.getLogger(RestTemplateClient.class);

    public static void sendNotification(NotificationDTO notificationDTO) {
        LOG.info("Send Notification {}: ", notificationDTO);
        REST_TEMPLATE.postForEntity(NOTIFY_DOCTORS_API, notificationDTO, Void.class);
    }

}
