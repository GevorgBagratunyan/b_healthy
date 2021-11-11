package com.blueteam.tracker.exception.doctor;

import com.blueteam.tracker.exception.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DoctorExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(DoctorExceptionHandler.class);

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ResponseError> handleDoctorNotFoundException(DoctorNotFoundException ex) {
        log.error("An Exception during requesting data -> {}, {}",
                ex.getMessage(), ex.getData());
        ResponseError responseError = new ResponseError(ex.getMessage(), ex.getData());
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }
}
