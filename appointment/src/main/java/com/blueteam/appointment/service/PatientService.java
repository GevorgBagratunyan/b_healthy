package com.blueteam.appointment.service;

import com.blueteam.appointment.dto.AppointmentDTO;
import com.blueteam.appointment.dto.PatientDTO;
import com.blueteam.appointment.entity.Appointment;
import com.blueteam.appointment.entity.Doctor;
import com.blueteam.appointment.entity.Patient;
import com.blueteam.appointment.exception.appointment.AppointmentNotFoundException;
import com.blueteam.appointment.exception.doctor.DoctorNotFoundException;
import com.blueteam.appointment.exception.patient.PatientNotFoundException;
import com.blueteam.appointment.repository.AppointmentRepository;
import com.blueteam.appointment.repository.DoctorRepository;
import com.blueteam.appointment.repository.PatientRepository;
import com.blueteam.appointment.service.crud.CRUD;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PatientService implements CRUD<PatientDTO, Long> {

    private final AppointmentRepository appointmentRepository;
    private final NotifierService notifierService;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public PatientService(AppointmentRepository appointmentRepository,
                          NotifierService notifierService,
                          PatientRepository patientRepository,
                          DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.notifierService = notifierService;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public void makeAnAppointment(AppointmentDTO appointmentDTO) {
        Long doctorId = appointmentDTO.getDoctorId();
        Long patientId = appointmentDTO.getPatientId();
        String str = appointmentDTO.getDate();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse(str, dtf);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(String.valueOf(doctorId), appointmentDTO));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(String.valueOf(patientId)));
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDate(date);
        patient.addAppointment(appointment);
        doctor.addAppointment(appointment);

        appointmentRepository.save(appointment);
        doctorRepository.save(doctor);
        patientRepository.save(patient);
    }

    public void cancelAnAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(String.valueOf(appointmentId)));
        appointment.setCanceled(true);
        notifierService.sendNotificationToDoctor(appointment);
    }

    @Override
    public PatientDTO create(PatientDTO patientDTO) {
        return null;
    }

    @Override
    public PatientDTO delete(Long aLong) {
        return null;
    }

    @Override
    public PatientDTO get(Long aLong) {
        return null;
    }

    @Override
    public PatientDTO update(PatientDTO patientDTO, Long aLong) {
        return null;
    }
}
