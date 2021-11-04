package com.blueteam.appointment.exception.appointment;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(String appointmentId) {
        super(String.format("Appointment by id :  {%s}  - Not found", appointmentId));
    }
}
