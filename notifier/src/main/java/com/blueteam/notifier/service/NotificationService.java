package com.blueteam.notifier.service;

import com.blueteam.notifier.dto.EmailAuthenticationDto;
import com.blueteam.notifier.dto.HemodynamicaDTO;
import com.blueteam.notifier.dto.NotificationDTO;
import com.blueteam.notifier.dto.SmsAuthenticationDto;
import com.blueteam.notifier.entity.AppointmentNotification;
import com.blueteam.notifier.entity.DoctorContacts;
import com.blueteam.notifier.entity.Hemodynamica;
import com.blueteam.notifier.entity.TrackingNotification;
import com.blueteam.notifier.repository.AppointmentNotificationRepository;
import com.blueteam.notifier.repository.TrackingNotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final TrackingNotificationRepository trackingNotificationRepository;
    private final AppointmentNotificationRepository appointmentNotificationRepository;
    private final EmailService emailService;
    private final SmsService smsService;

    public NotificationService(TrackingNotificationRepository trackingNotificationRepository,
                               AppointmentNotificationRepository appointmentNotificationRepository,
                               EmailService emailService,
                               SmsService smsService) {
        this.trackingNotificationRepository = trackingNotificationRepository;
        this.appointmentNotificationRepository = appointmentNotificationRepository;
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void notifyDoctors(NotificationDTO notificationDTO) {
        HemodynamicaDTO hemodynamicaDTO = notificationDTO.getCurrentAvgHemodynamica();
        String alertMsg = notificationDTO.getAlertMsg();
        String phoneNumber = notificationDTO.getPhoneNumber();
        String email = notificationDTO.getEmail();

        Hemodynamica hemodynamica = new Hemodynamica();
        BeanUtils.copyProperties(hemodynamicaDTO, hemodynamica);
        DoctorContacts contacts = new DoctorContacts();
        contacts.setDoctorsEmail(email);
        contacts.setDoctorsPhoneNumber(phoneNumber);

        TrackingNotification notification = new TrackingNotification();
        notification.setHemodynamica(hemodynamica);
        notification.setDoctorContacts(contacts);
        if (email != null) {
            emailService.sendEmail(email, "Health condition alerting", alertMsg);
        }
        if (phoneNumber != null) {
            smsService.sendSms(phoneNumber, alertMsg);
        }
        trackingNotificationRepository.save(notification);
    }

    public void authenticateWithSms(SmsAuthenticationDto smsAuthenticationDto) {
        String phoneNumber = smsAuthenticationDto.getPhoneNumber();
        String verificationCode = smsAuthenticationDto.getVerificationCode();
        smsService.sendSms(phoneNumber, verificationCode);
    }

    public void authenticateWithEmail(EmailAuthenticationDto emailAuthenticationDto) {
        String email = emailAuthenticationDto.getEmail();
        String verificationCode = emailAuthenticationDto.getVerificationCode();
        emailService.sendEmail(email, "Authentication", verificationCode);
    }

    public void sendNotification(NotificationDTO notificationDTO) {
        String email = notificationDTO.getEmail();
        String phoneNumber = notificationDTO.getPhoneNumber();
        String subject = notificationDTO.getSubject();
        String msg = notificationDTO.getMsg();
        AppointmentNotification notification = new AppointmentNotification();
        BeanUtils.copyProperties(notificationDTO, notification);
        if (email != null) {
            emailService.sendEmail(email, subject, msg);
        }
        if (phoneNumber != null) {
            smsService.sendSms(phoneNumber, msg);
        }
        appointmentNotificationRepository.save(notification);
    }
}
