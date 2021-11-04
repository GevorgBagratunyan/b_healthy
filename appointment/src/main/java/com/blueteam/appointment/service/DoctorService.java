package com.blueteam.appointment.service;

import com.blueteam.appointment.entity.Appointment;
import com.blueteam.appointment.exception.appointment.AppointmentNotFoundException;
import com.blueteam.appointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DoctorService {

    private final AppointmentRepository appointmentRepository;

    public DoctorService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void acceptOrDeclineAnAppointment(Boolean isAccepted, Long appointmentId) {

    }
}
