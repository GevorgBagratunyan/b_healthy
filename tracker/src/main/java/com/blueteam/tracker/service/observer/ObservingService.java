package com.blueteam.tracker.service.observer;

import com.blueteam.tracker.controller.RestTemplateClient;
import com.blueteam.tracker.dto.HemodynamicaDTO;
import com.blueteam.tracker.dto.NotificationDTO;
import com.blueteam.tracker.entity.Doctor;
import com.blueteam.tracker.entity.Hemodynamica;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ObservingService {

    public void notifyObservers(Long objId, String msg, Set<Doctor> doctors, List<Hemodynamica> hemodynamics) {
        Hemodynamica avg = calculateAvgHemodynamica(hemodynamics);
        for (Doctor doctor : doctors) {
            handleEvent(avg, objId, msg, doctor);
        }
    }

    //Notifies all observing doctors when hemodynamic parameters are dangerous
    public void observe(Long objId, Hemodynamica hemodynamica,
                        Set<Doctor> doctors, List<Hemodynamica> hemodynamics) {
        hemodynamics.add(hemodynamica);
        //if a significant amount of information is recorded, remove (first)old 100 records
        //7days*24hours*60minutes = 10080
        if (hemodynamics.size() > 10080) {
            hemodynamics.subList(0, 100).clear();
        }
        if (hemodynamics.size() < 10) {
            return;
        }
        if (isDangerous(hemodynamics)) {
            String msg = "IN DANDER!!!";
            notifyObservers(objId, msg, doctors, hemodynamics);
        }
    }

    private boolean isDangerous(List<Hemodynamica> hemodynamics) {
        Hemodynamica avgHemodynamica = calculateAvgHemodynamica(hemodynamics);
        Integer heartRateAVG = avgHemodynamica.getHeartRate();
        Integer saturationAVG = avgHemodynamica.getSaturation();

        return heartRateAVG > 90 || heartRateAVG < 60 || saturationAVG < 93;
    }

    //Calculates AVG params from last 10 records in the List
    private Hemodynamica calculateAvgHemodynamica(List<Hemodynamica> hemodynamics) {
        Integer sumHR = 0;
        Integer sumSAT = 0;
        for (int i = 1; i < 11; i++) {
            Hemodynamica hemodynamica = hemodynamics.get(hemodynamics.size() - i);
            sumHR += hemodynamica.getHeartRate();
            sumSAT += hemodynamica.getSaturation();
        }
        Integer heartRateAVG = sumHR / 10;
        Integer saturationAVG = sumSAT / 10;

        return new Hemodynamica(saturationAVG, heartRateAVG);
    }

    private void handleEvent(Hemodynamica hemodynamica, Long objId, String msg, Doctor doctor) {
        NotificationDTO notificationDTO = new NotificationDTO();
        HemodynamicaDTO hemodynamicaDTO = new HemodynamicaDTO();
        BeanUtils.copyProperties(hemodynamica, hemodynamicaDTO);
        notificationDTO.setAlertMsg(msg);
        notificationDTO.setCurrentAvgHemodynamica(hemodynamicaDTO);
        notificationDTO.setObjId(objId);
        notificationDTO.setEmail(doctor.getEmail());
        notificationDTO.setPhoneNumber(doctor.getPhoneNumber());
        RestTemplateClient.sendNotification(notificationDTO);
    }
}
