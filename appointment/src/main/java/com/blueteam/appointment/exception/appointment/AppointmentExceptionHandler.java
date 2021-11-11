package com.blueteam.appointment.exception.appointment;

import com.blueteam.appointment.exception.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppointmentExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(AppointmentNotFoundException.class);

    @ExceptionHandler(AppointmentNotFoundException.class)
    public ResponseEntity<Object> handleAppointmentNotFoundException(AppointmentNotFoundException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(), "No additional information");
        log.error("An Exception occurred while retrieving the data -> {}", responseError);
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }
}
