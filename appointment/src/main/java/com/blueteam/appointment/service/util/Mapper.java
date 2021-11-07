package com.blueteam.appointment.service.util;

import com.blueteam.appointment.dto.AppointmentDTO;
import com.blueteam.appointment.entity.Appointment;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Mapper {

    private Mapper() {

    }

    public static void mapAppointmentDTOsListToAppointmentsList (List<AppointmentDTO> appointmentDTOs, List<Appointment> appointments) {
        for(AppointmentDTO appointmentDTO : appointmentDTOs) {
            Appointment appointment = new Appointment();
            BeanUtils.copyProperties(appointmentDTO, appointment);
            String str = appointmentDTO.getDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime date = LocalDateTime.parse(str, dtf);
            appointment.setDate(date);
            appointments.add(appointment);
        }
    }

    public static void mapAppointmentsListToAppointmentDTOsList(List<Appointment> appointments, List<AppointmentDTO> appointmentDTOs) {
        for(Appointment appointment : appointments) {
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            BeanUtils.copyProperties(appointment, appointmentDTO);
            appointmentDTO.setDate(appointmentDTO.getDate());
            appointmentDTOs.add(appointmentDTO);
        }
    }
}
