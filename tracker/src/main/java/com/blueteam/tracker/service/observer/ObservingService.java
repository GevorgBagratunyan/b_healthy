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

    //Notifies all observing doctors when hemodynamic parameters are dangerous
    public void observe(Long objId, String name, String phoneNumber, Hemodynamica hemodynamica,
                        Set<Doctor> doctors, List<Hemodynamica> hemodynamics) {
        hemodynamics.add(hemodynamica);

        if (hemodynamics.size() < 10) {
            return;
        }
        if (isDangerous(hemodynamics)) {
            String msg = "Patient -> " + name +" with id: " +
                    objId +" ,and with phone number: " + phoneNumber +
                    " ,has dangerous Hemodynamic parameters !!!" +
                    "\nHeart rate is: " + hemodynamica.getHeartRate() +
                    ", SpO2 is: " + hemodynamica.getSaturation();
            notifyObservers(objId, name, msg, doctors, hemodynamics);
        }
    }

    private void notifyObservers(Long objId, String name, String msg, Set<Doctor> doctors,
                                 List<Hemodynamica> hemodynamics) {
        Hemodynamica avg = calculateAvgHemodynamica(hemodynamics);
        for (Doctor doctor : doctors) {
            NotificationDTO notificationDTO = new NotificationDTO();
            HemodynamicaDTO hemodynamicaDTO = new HemodynamicaDTO();
            BeanUtils.copyProperties(avg, hemodynamicaDTO);
            notificationDTO.setAlertMsg(msg);
            notificationDTO.setCurrentAvgHemodynamica(hemodynamicaDTO);
            notificationDTO.setObjId(objId);
            notificationDTO.setName(name);
            notificationDTO.setEmail(doctor.getEmail());
            notificationDTO.setPhoneNumber(doctor.getPhoneNumber());
            RestTemplateClient.sendNotification(notificationDTO);
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
        //Grab the last 10 records and calculate
        for (int i = 1; i < 11; i++) {
            Hemodynamica hemodynamica = hemodynamics.get(hemodynamics.size() - i);
            sumHR += hemodynamica.getHeartRate();
            sumSAT += hemodynamica.getSaturation();
        }
        Integer heartRateAVG = sumHR / 10;
        Integer saturationAVG = sumSAT / 10;

        return new Hemodynamica(saturationAVG, heartRateAVG);
    }
}
