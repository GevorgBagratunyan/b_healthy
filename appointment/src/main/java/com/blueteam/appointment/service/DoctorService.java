package com.blueteam.appointment.service;

import com.blueteam.appointment.dto.DoctorDTO;
import com.blueteam.appointment.entity.Appointment;
import com.blueteam.appointment.entity.Doctor;
import com.blueteam.appointment.exception.appointment.AppointmentNotFoundException;
import com.blueteam.appointment.exception.doctor.DoctorNotFoundException;
import com.blueteam.appointment.repository.AppointmentRepository;
import com.blueteam.appointment.repository.DoctorRepository;
import com.blueteam.appointment.service.crud.CRUD;
import com.blueteam.appointment.service.util.Mapper;
import org.springframework.beans.BeanUtils;
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
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO, doctor);
        Doctor saved = doctorRepository.save(doctor);
        DoctorDTO responseDTO = new DoctorDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        responseDTO.setDoctorId(saved.getId());
        return responseDTO;
    }

    @Override
    public DoctorDTO delete(Long id) {
        DoctorDTO responseDTO = new DoctorDTO();
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id.toString(), id));
        BeanUtils.copyProperties(doctor, responseDTO);
        responseDTO.setDoctorId(doctor.getId());
        Mapper.mapAppointmentsListToAppointmentDTOsList(doctor.getAppointments(),
                responseDTO.getAppointmentDTOs());
        doctorRepository.deleteById(id);
        return responseDTO;
    }

    @Override
    public DoctorDTO get(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id.toString(), id));
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor, doctorDTO);
        doctorDTO.setDoctorId(doctor.getId());
        Mapper.mapAppointmentsListToAppointmentDTOsList(doctor.getAppointments(),
                doctorDTO.getAppointmentDTOs());
        return doctorDTO;
    }

    @Override
    public DoctorDTO update(DoctorDTO doctorDTO, Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id.toString(), id));
        BeanUtils.copyProperties(doctorDTO, doctor);
        Doctor saved = doctorRepository.save(doctor);
        DoctorDTO responseDTO = new DoctorDTO();
        BeanUtils.copyProperties(saved, responseDTO);
        Mapper.mapAppointmentsListToAppointmentDTOsList(saved.getAppointments(),
                responseDTO.getAppointmentDTOs());
        responseDTO.setDoctorId(saved.getId());
        return responseDTO;
    }
}
