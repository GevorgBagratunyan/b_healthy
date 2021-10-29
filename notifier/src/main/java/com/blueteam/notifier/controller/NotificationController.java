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
