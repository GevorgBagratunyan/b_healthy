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
        Map<String, String> emailsAndPhoneNumbers = notificationDTO.getEmailsAndPhoneNumbers();
        HemodynamicaDTO hemodynamicaDTO = notificationDTO.getCurrentAvgHemodynamica();
        String alertMsg = notificationDTO.getAlertMsg();
        Long objId = notificationDTO.getObjId();
        Hemodynamica hemodynamica = new Hemodynamica();
        BeanUtils.copyProperties(hemodynamicaDTO, hemodynamica);
        List<DoctorContacts> contactsList = new ArrayList<>();

        for(Map.Entry<String, String> set : emailsAndPhoneNumbers.entrySet()) {
            DoctorContacts contacts = new DoctorContacts();
            String email = set.getKey();
            String phoneNumber = set.getValue();
            contacts.setDoctorsEmail(email);
            contacts.setDoctorsPhoneNumber(phoneNumber);
            contactsList.add(contacts);
        }
            Notification notification = new Notification();
            notification.setObjId(objId);
            notification.setHemodynamica(hemodynamica);
            notification.setDoctorContacts(contactsList);
            notificationRepository.save(notification);
            alertingService.alert(notification, alertMsg);
    }
}
