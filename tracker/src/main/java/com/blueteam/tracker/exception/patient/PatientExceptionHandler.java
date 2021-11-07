package com.blueteam.tracker.exception.patient;

import com.blueteam.tracker.exception.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PatientExceptionHandler {

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Object> handlePatientNotFoundException(PatientNotFoundException ex) {
        ResponseError responseError = new ResponseError(ex.getMessage(), ex.getData());
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

}
