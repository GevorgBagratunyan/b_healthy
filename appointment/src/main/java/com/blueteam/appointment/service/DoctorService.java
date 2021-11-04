package com.blueteam.appointment.service;

import com.blueteam.appointment.dto.DoctorDTO;
import com.blueteam.appointment.entity.Appointment;
import com.blueteam.appointment.exception.appointment.AppointmentNotFoundException;
import com.blueteam.appointment.repository.AppointmentRepository;
import com.blueteam.appointment.repository.DoctorRepository;
import com.blueteam.appointment.service.crud.CRUD;
import org.springframework.stereotype.Service;

@Service
public class DoctorService implements CRUD<DoctorDTO, Long> {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final NotifierService notifierService;

    public DoctorService(AppointmentRepository appointmentRepository,
                         DoctorRepository doctorRepository,
                         NotifierService notifierService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.notifierService = notifierService;
    }

    //When the Doctor accepts or declines the appointment,
    //a notification sent to a Patient with information about it
    public void acceptOrDeclineAnAppointment(Boolean isAccepted, Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(String.valueOf(appointmentId)));
        appointment.setAccepted(isAccepted);
        notifierService.sendNotificationToPatient(appointment, isAccepted);
        appointmentRepository.save(appointment);
    }

    @Override
    public DoctorDTO create(DoctorDTO doctorDTO) {
        return null;
    }

    @Override
    public DoctorDTO delete(Long aLong) {
        return null;
    }

    @Override
    public DoctorDTO get(Long aLong) {
        return null;
    }

    @Override
    public DoctorDTO update(DoctorDTO doctorDTO, Long aLong) {
        return null;
    }
}
