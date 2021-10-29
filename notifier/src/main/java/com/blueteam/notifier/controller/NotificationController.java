package com.blueteam.notifier.controller;

import com.blueteam.notifier.dto.NotificationDTO;
import com.blueteam.notifier.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private static final String ACCOUNT_SID = "ACf8751052d983ad03251129ce8f0c7e98";
    private static final String AUTH_TOKEN = "91e63ea44f0483b36048a1436ebcdafd";
    private static final String TWILIO_NUMBER = "+12075013766";

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> notifyDoctors(
            @RequestBody NotificationDTO notificationDTO) {
        notificationService.notifyDoctors(notificationDTO);
        return ResponseEntity.ok().build();
    }
}
