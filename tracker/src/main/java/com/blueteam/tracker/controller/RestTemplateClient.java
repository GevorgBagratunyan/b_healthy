package com.blueteam.tracker.controller;

import com.blueteam.tracker.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestTemplateClient {
    @Value("${NOTIFY_DOCTORS_API}")
    private String NOTIFY_DOCTORS_API;
    private final RestTemplate REST_TEMPLATE;
    private final Logger log;

    public RestTemplateClient(RestTemplate rest_template) {
        REST_TEMPLATE = rest_template;
        this.log = LoggerFactory.getLogger(RestTemplateClient.class);
    }

    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("Send Notification {}: ", notificationDTO);
        REST_TEMPLATE.postForEntity(NOTIFY_DOCTORS_API, notificationDTO, Void.class);
    }

}
