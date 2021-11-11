package com.blueteam.tracker.exception.patient;

import com.blueteam.tracker.exception.ResponseError;
import com.blueteam.tracker.exception.doctor.DoctorExceptionHandler;
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
    public ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException ex) {
        log.error("An Exception occurred during requesting data -> msg: {}, data: {}",
                ex.getMessage(), ex.getData());
        ResponseError responseError = new ResponseError(ex.getMessage(), ex.getData());
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

}
