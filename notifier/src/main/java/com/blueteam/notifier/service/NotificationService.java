package com.blueteam.notifier.service;

import com.blueteam.notifier.dto.HemodynamicaDTO;
import com.blueteam.notifier.dto.NotificationDTO;
import com.blueteam.notifier.entity.DoctorContacts;
import com.blueteam.notifier.entity.Hemodynamica;
import com.blueteam.notifier.entity.Notification;
import com.blueteam.notifier.repository.NotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final AlertingService alertingService;

    public NotificationService(NotificationRepository notificationRepository, AlertingService alertingService) {
        this.notificationRepository = notificationRepository;
        this.alertingService = alertingService;
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
}
