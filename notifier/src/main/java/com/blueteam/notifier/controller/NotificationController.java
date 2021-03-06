package com.blueteam.notifier.controller;

import com.blueteam.notifier.dto.EmailAuthenticationDto;
import com.blueteam.notifier.dto.NotificationDTO;
import com.blueteam.notifier.dto.SmsAuthenticationDto;
import com.blueteam.notifier.service.NotificationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/notifier/notify")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> notifyDoctors (
            @Valid @RequestBody NotificationDTO notificationDTO) {
        notificationService.notifyDoctors(notificationDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/patient")
    public ResponseEntity<Void> notifyPatient(
            @Valid @RequestBody NotificationDTO notificationDTO) {
        notificationService.sendNotification(notificationDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/doctor")
    public ResponseEntity<Void> notifyDoctor(
            @Valid @RequestBody NotificationDTO notificationDTO) {
        notificationService.sendNotification(notificationDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sms-authentication")
    public ResponseEntity<Void> authenticateWithSms(
            @Valid @RequestBody SmsAuthenticationDto smsAuthenticationDto) {
        notificationService.authenticateWithSms(smsAuthenticationDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/email-authentication")
    public ResponseEntity<Void> authenticateWithEmail(
            @Valid @RequestBody EmailAuthenticationDto emailAuthenticationDto) {
        notificationService.authenticateWithEmail(emailAuthenticationDto);
        return ResponseEntity.ok().build();
    }
}
