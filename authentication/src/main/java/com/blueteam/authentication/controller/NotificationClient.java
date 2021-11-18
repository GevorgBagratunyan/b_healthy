package com.blueteam.authentication.controller;

import com.blueteam.authentication.dto.NotificationEmailDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationClient {
    private static final String EMAIL_AUTHENTICATION_API =
            "http://3.17.28.235:5000/notifier/notify/email-authentication";
    private static final String SMS_AUTHENTICATION_API =
            "http://3.17.28.235:5000/notifier/notify/sms-authentication";
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();


    public static void sendNotificationEmail(NotificationEmailDTO emailDTO) {
        REST_TEMPLATE.postForEntity(EMAIL_AUTHENTICATION_API, emailDTO, Void.class);
    }


}
