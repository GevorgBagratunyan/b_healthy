package com.blueteam.appointment.exception.appointment;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(String appointmentId) {
        super(String.format("Appointment with given id :  {%s}  - Not found", appointmentId));
    }
}
