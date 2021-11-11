package com.blueteam.appointment.exception.patient;

import com.blueteam.appointment.exception.ResponseError;
import com.blueteam.appointment.exception.doctor.DoctorExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PatientExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(PatientExceptionHandler.class);


    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ResponseError> handlePatientNotFoundException(PatientNotFoundException ex) {
        ResponseError responseError = new ResponseError(
                ex.getMessage(), "No additional information");
        log.error("An Exception occurred while retrieving the data -> {}", responseError);
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

}
