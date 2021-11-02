package com.blueteam.notifier.service;

import com.blueteam.notifier.dto.EmailAuthenticationDto;
import com.blueteam.notifier.dto.HemodynamicaDTO;
import com.blueteam.notifier.dto.NotificationDTO;
import com.blueteam.notifier.dto.SmsAuthenticationDto;
import com.blueteam.notifier.entity.DoctorContacts;
import com.blueteam.notifier.entity.Hemodynamica;
import com.blueteam.notifier.entity.Notification;
import com.blueteam.notifier.repository.NotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final AlertingService alertingService;
    private final EmailService emailService;
    private final SmsService smsService;

    public NotificationService(NotificationRepository notificationRepository,
                               AlertingService alertingService,
                               EmailService emailService,
                               SmsService smsService) {
        this.notificationRepository = notificationRepository;
        this.alertingService = alertingService;
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void notifyDoctors(NotificationDTO notificationDTO) {
        HemodynamicaDTO hemodynamicaDTO = notificationDTO.getCurrentAvgHemodynamica();
        String alertMsg = notificationDTO.getAlertMsg();
        String phoneNumber = notificationDTO.getPhoneNumber();
        String email = notificationDTO.getEmail();
        Long objId = notificationDTO.getObjId();

        Hemodynamica hemodynamica = new Hemodynamica();
        BeanUtils.copyProperties(hemodynamicaDTO, hemodynamica);
        DoctorContacts contacts = new DoctorContacts();
        contacts.setDoctorsEmail(email);
        contacts.setDoctorsPhoneNumber(phoneNumber);

        Notification notification = new Notification();
        notification.setObjId(objId);
        notification.setHemodynamica(hemodynamica);
        notification.setDoctorContacts(contacts);

        notificationRepository.save(notification);
        alertingService.alert(notification, alertMsg);
    }

    public  void authenticateWithSms(SmsAuthenticationDto smsAuthenticationDto) {
        String phoneNumber = smsAuthenticationDto.getPhoneNumber();
        String verificationCode = smsAuthenticationDto.getVerificationString();
        smsService.sendSms(phoneNumber, verificationCode);
    }

    public  void authenticateWithEmail(EmailAuthenticationDto emailAuthenticationDto) {
        String email = emailAuthenticationDto.getEmail();
        String verificationCode = emailAuthenticationDto.getVerificationCode();
        emailService.sendEmail(email, "Authentication", verificationCode);
    }
}
